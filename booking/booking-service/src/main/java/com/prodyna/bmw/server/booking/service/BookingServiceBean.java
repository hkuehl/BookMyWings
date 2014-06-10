package com.prodyna.bmw.server.booking.service;

import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.slf4j.Logger;

import com.prodyna.bmw.server.aircraft.Aircraft;
import com.prodyna.bmw.server.booking.Booking;
import com.prodyna.bmw.server.booking.BookingService;
import com.prodyna.bmw.server.pilot.Pilot;
import com.prodyna.bmw.server.pilot.PilotService;

/**
 * @author Henry Kuehl, PRODYNA AG
 * 
 */
@Stateless
public class BookingServiceBean implements BookingService {

	@Inject
	private EntityManager em;

	@Inject
	private PilotService pilotService;

	@Inject
	private Logger LOG;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.prodyna.bmw.server.booking.BookingService#addBooking(com.prodyna.
	 * bmw.server.booking.Booking)
	 */
	@Override
	public Booking addBooking(Booking booking) {
		em.persist(booking);
		LOG.info("Added Booking: " + booking);
		return booking;
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
	 * com.prodyna.bmw.server.booking.BookingService#findAircraftsForPilot(java
	 * .lang.String)
	 */
	@Override
	public List<Aircraft> findAircraftsForPilot(String pilotId) {

		Pilot pilot = pilotService.getPilot(pilotId);

		List<Aircraft> resultList = em
				.createNamedQuery(Booking.QUERY_FIND_AIRCRAFTS_FOR_PILOT,
						Aircraft.class)
				.setParameter(Booking.QUERY_PARAMETER_PILOT, pilot)
				.getResultList();

		return resultList;
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
		return Collections.emptyList();
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
	 * com.prodyna.bmw.server.booking.BookingService#updateBooking(com.prodyna
	 * .bmw.server.booking.Booking)
	 */
	@Override
	public void updateBooking(Booking booking) {

		em.merge(booking);
	}
}
