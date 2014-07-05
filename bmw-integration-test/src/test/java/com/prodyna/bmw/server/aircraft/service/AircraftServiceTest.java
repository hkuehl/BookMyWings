package com.prodyna.bmw.server.aircraft.service;

import javax.inject.Inject;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.prodyna.bmw.server.aircraft.Aircraft;
import com.prodyna.bmw.server.aircraft.AircraftService;
import com.prodyna.bmw.server.aircraft.type.AircraftTypeService;

/**
 * @author Henry Kuehl, PRODYNA AG
 */
@RunWith(Arquillian.class)
public class AircraftServiceTest {

	private static final String D_EKF22 = "D-EKF22";

	@Inject
	private AircraftTypeService aircraftTypeService;

	@Inject
	private AircraftService aircraftService;

	@Test
	public void readAircraftByType() {
		Assert.assertEquals(
				this.getClass().getName() + "#registration",
				aircraftService
						.getAircraftsForType(
								this.getClass().getName() + "#aircraftType")
						.get(0).getRegistration());

	}

	@Test
	public void testCRUDAircraft() {

		Aircraft aircraft = new Aircraft();
		aircraft.setRegistration(D_EKF22);
		aircraft.setAircraftType(aircraftTypeService.getAircraftType(this
				.getClass().getName() + "#aircraftType"));
		aircraft = aircraftService.addAircraft(aircraft);

		// does the DB generate a uuid??
		Assert.assertNotNull(aircraftService.getAircraft(aircraft.getId()));

		// check for equality
		Assert.assertEquals(aircraft,
				aircraftService.getAircraft(aircraft.getId()));

		// update
		aircraft.setRegistration("D-EKF23");
		aircraftService.updateAircraft(aircraft);
		Assert.assertEquals("D-EKF23",
				aircraftService.getAircraft(aircraft.getId()).getRegistration());

		// delete
		aircraftService.deleteAircraft(aircraft.getId());
		Assert.assertNull(aircraftService.getAircraft(aircraft.getId()));
	}
}
