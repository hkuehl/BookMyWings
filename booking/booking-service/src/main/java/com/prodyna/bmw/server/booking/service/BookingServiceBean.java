package com.prodyna.bmw.server.booking.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

import com.prodyna.bmw.server.booking.Booking;
import com.prodyna.bmw.server.booking.BookingService;
import com.prodyna.bmw.server.booking.BookingState;
import com.prodyna.bmw.server.common.monitoring.Monitored;
import com.prodyna.bmw.server.license.PilotLicense;
import com.prodyna.bmw.server.license.PilotLicenseService;

/**
 * @author Henry Kuehl, PRODYNA AG
 * 
 */
@Monitored
@Stateless
public class BookingServiceBean implements BookingService {

	@Inject
	private EntityManager em;

	@Inject
	private PilotLicenseService pilotLicenseService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.prodyna.bmw.server.booking.BookingService#addBooking(com.prodyna.
	 * bmw.server.booking.Booking)
	 */
	@Override
	public Booking addBooking(Booking booking) {

		List<PilotLicense> licenses = pilotLicenseService
				.findLicenseForPilotAndAircraftType(booking.getPilot().getId(),
						booking.getAircraft().getAircraftType().getId());

		List<PilotLicense> validLicenses = new ArrayList<PilotLicense>();
		for (PilotLicense license : licenses) {
			if (license.getValidThru().after(booking.getEnd())) {
				validLicenses.add(license);
			}
		}

		if (validLicenses.isEmpty()) {
			throw new RuntimeException("No valid License found for Booking "
					+ booking.toString());
		}

		em.persist(booking);
		return booking;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.prodyna.bmw.server.booking.BookingStateService#possibleTransisitons
	 * (java.lang.String)
	 */
	@Override
	public List<BookingState> allowedTransisitons(String bookingId) {
		Booking booking = em.find(Booking.class, bookingId);
		return booking.getBookingState().allowedTransitions();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.prodyna.bmw.server.booking.BookingStateService#bringBack(com.prodyna
	 * .bmw.server.booking.Booking)
	 */
	@Override
	public Booking bringBack(String bookingId) {
		return setState(bookingId, BookingState.FINISHED);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.prodyna.bmw.server.booking.BookingStateService#cancel(com.prodyna
	 * .bmw.server.booking.Booking)
	 */
	@Override
	public Booking cancel(String bookingId) {
		return setState(bookingId, BookingState.CANCELLED);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.prodyna.bmw.server.booking.BookingService#deleteBooking(java.lang
	 * .String)
	 */
	@Override
	public void deleteBooking(String uuid) {

		Booking booking = readBookingForId(uuid);
		em.remove(booking);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.prodyna.bmw.server.booking.BookingService#readAllBookings(java.lang
	 * .Integer, java.lang.Integer)
	 */
	@Override
	public List<Booking> readAllBookings(Integer start, Integer pageSize) {
		return em
				.createNamedQuery(Booking.QUERY_GET_ALL_BOOKINGS_PAGINATED,
						Booking.class).setFirstResult(start)
				.setMaxResults(pageSize).getResultList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.prodyna.bmw.server.booking.BookingService#getBooking(java.lang.String
	 * )
	 */
	@Override
	public Booking readBookingForId(String uuid) {
		List<Booking> resultList = em
				.createNamedQuery(Booking.QUERY_FIND_BOOKING_BY_ID,
						Booking.class)
				.setParameter(Booking.QUERY_PARAMETER_BOOKINGID, uuid)
				.getResultList();
		return resultList.isEmpty() ? null : resultList.get(0);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.prodyna.bmw.server.booking.BookingService#readBookingsForMonth(java
	 * .lang.Integer)
	 */
	@Override
	public List<Booking> readBookingsForMonth(final Integer month) {
		List<Booking> allBookings = em.createNamedQuery(
				Booking.QUERY_GET_ALL_BOOKINGS_PAGINATED, Booking.class)
				.getResultList();
		final Calendar calendar = Calendar.getInstance();
		CollectionUtils.filter(allBookings, new Predicate() {
			@Override
			public boolean evaluate(Object arg0) {
				Booking booking = (Booking) arg0;
				calendar.setTime(booking.getStart());
				boolean startsInThisMonth = calendar.get(Calendar.MONTH) + 1 == month
						.intValue();
				calendar.setTime(booking.getEnd());
				boolean endsInThisMonth = calendar.get(Calendar.MONTH) + 1 == month
						.intValue();
				return startsInThisMonth || endsInThisMonth;
			}
		});

		return allBookings;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.prodyna.bmw.server.booking.BookingService#readBookingsForPilot(java
	 * .lang.String)
	 */
	@Override
	public List<Booking> readBookingsForPilot(String pilotId) {
		return em
				.createNamedQuery(Booking.QUERY_FIND_BOOKING_FOR_PILOT,
						Booking.class)
				.setParameter(Booking.QUERY_PARAMETER_PILOT_ID, pilotId)
				.getResultList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.prodyna.bmw.server.booking.BookingStateService#reserve(com.prodyna
	 * .bmw.server.booking.Booking)
	 */
	@Override
	public Booking reserve(String bookingId) {
		return setState(bookingId, BookingState.RESERVED);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.prodyna.bmw.server.booking.BookingStateService#retrieve(com.prodyna
	 * .bmw.server.booking.Booking)
	 */
	@Override
	public Booking retrieve(String bookingId) {
		return setState(bookingId, BookingState.LENT);
	}

	private Booking setState(String bookingId, BookingState bookingState) {
		Booking booking = em.find(Booking.class, bookingId);
		if (booking == null) {
			throw new RuntimeException("Booking with id " + bookingId
					+ " not found in DB");
		}

		if (booking.getBookingState().allowedTransitions()
				.contains(bookingState)) {
			booking.setBookingState(bookingState);
			return em.merge(booking);
		}

		throw new RuntimeException(booking.toString()
				+ " was not allowed to go into state " + bookingState);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.prodyna.bmw.server.booking.BookingStateService#setState(com.prodyna
	 * .bmw.server.booking.Booking, com.prodyna.bmw.server.booking.BookingState)
	 */
	@Override
	public Booking setState(String bookingId, String bookingStateString) {
		BookingState bookingState = BookingState.fromString(bookingStateString);
		return setState(bookingId, bookingState);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.prodyna.bmw.server.booking.BookingService#updateBooking(com.prodyna
	 * .bmw.server.booking.Booking)
	 */
	@Override
	public void updateBooking(Booking booking) {
		em.merge(booking);
	}
}
