package com.prodyna.bmw.server.booking.service;

import java.util.List;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.prodyna.bmw.server.booking.Booking;
import com.prodyna.bmw.server.booking.BookingState;
import com.prodyna.bmw.server.common.monitoring.Monitored;

/**
 * @author Henry Kuehl, PRODYNA AG
 * 
 */
@Singleton
@Monitored
public class BookingStatusScheduler {

	@Inject
	private EntityManager em;

	@Schedule(minute = "*/5", hour = "*")
	public void scheduleBookingStatusUpdates() {
		List<Booking> resultList = em.createNamedQuery(
				Booking.QUERY_FIND_BOOKING_OVERDUE, Booking.class)
				.getResultList();
		for (Booking booking : resultList) {
			booking.setBookingState(BookingState.FINISHED);
			em.merge(booking);
		}
	}

}
