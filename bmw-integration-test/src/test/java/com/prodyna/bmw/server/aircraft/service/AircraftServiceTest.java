package com.prodyna.bmw.server.aircraft.service;

import javax.inject.Inject;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.prodyna.bmw.server.aircraft.Aircraft;
import com.prodyna.bmw.server.aircraft.AircraftService;
import com.prodyna.bmw.server.aircraft.type.AircraftType;
import com.prodyna.bmw.server.aircraft.type.AircraftTypeService;

/**
 * @author Henry Kuehl, PRODYNA AG
 */
@RunWith(Arquillian.class)
public class AircraftServiceTest {

	@Inject
	private AircraftService aircraftService;

	@Inject
	private AircraftTypeService aircraftTypeService;

	@Test
	public void testCRUDAircraft() {
		AircraftType aircraftType = new AircraftType();
		aircraftType.setTypeString("Boeasdsfsdf");
		aircraftType = aircraftTypeService.addAircraftType(aircraftType);
		Aircraft aircraft = new Aircraft();
		aircraft.setRegistration("D-EKF22");
		aircraft.setAircraftType(aircraftType);
		aircraft = aircraftService.addAircraft(aircraft);

		// does the DB generate a uuid??
		Assert.assertTrue(aircraftService.getAircraft(aircraft.getId()) != null);

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
