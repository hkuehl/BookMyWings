package com.prodyna.bmw.server.booking.service;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.prodyna.bmw.server.aircraft.Aircraft;
import com.prodyna.bmw.server.aircraft.AircraftService;
import com.prodyna.bmw.server.booking.Booking;
import com.prodyna.bmw.server.booking.BookingService;
import com.prodyna.bmw.server.pilot.Pilot;
import com.prodyna.bmw.server.pilot.PilotService;

/**
 * @author Henry Kuehl, PRODYNA AG
 * 
 */
@RunWith(Arquillian.class)
public class BookingServiceTest {

	@Inject
	private BookingService bookingService;

	@Inject
	private AircraftService aircraftService;

	@Inject
	private PilotService pilotService;

	private Aircraft aircraft, aircraft2;
	private Pilot pilot;
	private Booking booking;

	@Before
	public void setUp() {
		aircraft = new Aircraft();
		aircraft.setRegistration("D-EFGH");
		aircraftService.addAircraft(aircraft);

		aircraft2 = new Aircraft();
		aircraft2.setRegistration("X-XXXXX");
		aircraftService.addAircraft(aircraft2);

		pilot = new Pilot();
		pilot.setFirstName("Henry");
		pilot.setLastName("Keeeewwwl");
		pilotService.addPilot(pilot);

		booking = new Booking();
		booking.setStart(new Date());
		booking.setEnd(booking.getStart());
		booking.setAircraft(aircraft);
		booking.setPilot(pilot);
		bookingService.addBooking(booking);
	}

	@After
	public void tearDown() {
	}

	@Test
	@InSequence(1)
	public void testCRUDBooking() {
		Booking bookingDB = bookingService.readBookingForId(booking
				.getBookingId());
		Assert.assertEquals(booking, bookingDB);

		bookingDB.setEnd(new Date(new Date().getTime() + 1000));
		bookingService.updateBooking(bookingDB);
		bookingDB = bookingService.readBookingForId(booking.getBookingId());
		Assert.assertNotEquals(booking, bookingDB);

		bookingService.deleteBooking(booking.getBookingId());
		Assert.assertNull(bookingService.readBookingForId(booking
				.getBookingId()));

	}

	@Test(expected = Exception.class)
	@InSequence(2)
	public void testDuplicateBookingException() {
		Booking b2 = new Booking();
		b2.setStart(booking.getStart());
		b2.setEnd(booking.getEnd());
		b2.setAircraft(booking.getAircraft());
		b2.setPilot(booking.getPilot());
		bookingService.addBooking(b2);
	}

	@Test
	@InSequence(3)
	public void testFindAircraftsForPilot() throws InterruptedException {

		List<Aircraft> aircraftsForPilot = bookingService
				.findAircraftsForPilot(pilot.getId());
		Assert.assertTrue(aircraftsForPilot.size() == 1);
		Assert.assertTrue(aircraftsForPilot.get(0).equals(aircraft));
	}
}
