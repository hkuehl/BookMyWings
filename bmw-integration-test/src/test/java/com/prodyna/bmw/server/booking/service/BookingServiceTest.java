package com.prodyna.bmw.server.booking.service;

import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

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
import com.prodyna.bmw.server.aircraft.type.AircraftType;
import com.prodyna.bmw.server.aircraft.type.AircraftTypeService;
import com.prodyna.bmw.server.booking.Booking;
import com.prodyna.bmw.server.booking.BookingService;
import com.prodyna.bmw.server.booking.BookingState;
import com.prodyna.bmw.server.license.PilotLicense;
import com.prodyna.bmw.server.license.PilotLicenseService;
import com.prodyna.bmw.server.pilot.Pilot;
import com.prodyna.bmw.server.pilot.PilotRole;
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
	private AircraftTypeService aircraftTypeService;

	@Inject
	private PilotService pilotService;

	@Inject
	private PilotLicenseService pilotLicenseService;

	private Aircraft aircraft, aircraft2;
	private Pilot pilot;
	private Booking booking;

	@Before
	public void setUp() throws Exception {
		AircraftType type = new AircraftType();
		type.setTypeString("Cesnaaaa" + ThreadLocalRandom.current().nextInt());
		type = aircraftTypeService.addAircraftType(type);

		aircraft = new Aircraft();
		aircraft.setAircraftType(type);
		aircraft.setRegistration("D-EFGH"
				+ ThreadLocalRandom.current().nextInt());
		aircraft = aircraftService.addAircraft(aircraft);

		aircraft2 = new Aircraft();
		aircraft2.setAircraftType(type);
		aircraft2.setRegistration("X-XXXXX"
				+ ThreadLocalRandom.current().nextInt());
		aircraft2 = aircraftService.addAircraft(aircraft2);

		pilot = new Pilot();
		pilot.setFirstName("Henry");
		pilot.setLastName("Keeeewwwl");
		pilot.setUserName("HenKue" + ThreadLocalRandom.current().nextInt());

		Field passwdField = pilot.getClass().getDeclaredField("password");
		passwdField.setAccessible(true);
		passwdField.set(pilot, "passwd");

		PilotRole pilotRole = new PilotRole();
		pilotRole.setPilotRole("admin");
		pilotRole.setPilotRoleMappingId("1");
		pilot.setPilotRole(pilotRole);
		pilot = pilotService.addPilot(pilot);

		PilotLicense license = new PilotLicense();
		license.setAircraftType(type);
		license.setPilot(pilot);

		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2014);
		calendar.set(Calendar.MONTH, Calendar.JANUARY);
		calendar.set(Calendar.DAY_OF_MONTH, 1);

		license.setValidFrom(calendar.getTime());
		calendar.set(Calendar.YEAR, 2020);
		license.setValidThru(calendar.getTime());
		pilotLicenseService.addLicense(license);

		booking = new Booking();
		booking.setStart(new Date());
		booking.setEnd(booking.getStart());
		booking.setAircraft(aircraft);
		booking.setPilot(pilot);

		bookingService.addBooking(booking);
	}

	@After
	public void tearDown() {
		try {
			bookingService.deleteBooking(booking.getBookingId());
			aircraftService.deleteAircraft(aircraft.getId());
			pilotService.deletePilot(pilot.getId());
		} catch (Exception e) {
			// ok
		}
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
	@InSequence(4)
	public void testFindBookingByMonth() {
		List<Booking> bookingForMonth = bookingService
				.readBookingsForMonth(Calendar.getInstance()
						.get(Calendar.MONTH) + 1);

		Assert.assertEquals(booking, bookingForMonth.get(0));
	}

	@Test
	@InSequence(5)
	public void testFindBookingForPilot() {
		List<Booking> bookingForPilot = bookingService
				.readBookingsForPilot(pilot.getId());

		Assert.assertEquals(booking, bookingForPilot.get(0));
	}

	@Test
	@InSequence(3)
	public void testStateTransitions() {
		Assert.assertEquals(BookingState.RESERVED,
				bookingService.reserve(booking.getBookingId())
						.getBookingState());
		Assert.assertEquals(BookingState.LENT,
				bookingService.setState(booking.getBookingId(), "LEnt")
						.getBookingState());

		Assert.assertEquals(BookingState.FINISHED,
				bookingService.bringBack(booking.getBookingId())
						.getBookingState());
	}
}
