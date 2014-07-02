package com.prodyna.bmw.server.pilot.service;

import java.util.Date;

import javax.inject.Inject;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.prodyna.bmw.server.aircraft.type.AircraftType;
import com.prodyna.bmw.server.aircraft.type.AircraftTypeService;
import com.prodyna.bmw.server.license.PilotLicense;
import com.prodyna.bmw.server.license.PilotLicenseService;
import com.prodyna.bmw.server.pilot.Pilot;
import com.prodyna.bmw.server.pilot.PilotService;

/**
 * @author Henry Kuehl, PRODYNA AG
 * 
 */
@RunWith(Arquillian.class)
public class PilotLicenseServiceTest {

	@Inject
	private PilotLicenseService pilotLicenseService;

	@Inject
	private PilotService pilotService;

	@Inject
	private AircraftTypeService aircraftTypeService;

	@Test
	public void testCRUDPilotLicenseService() {
		AircraftType aircraftType = new AircraftType();
		aircraftType.setTypeString("Airbus");
		aircraftTypeService.addAircraftType(aircraftType);

		Pilot pilot = new Pilot();
		pilot.setFirstName("Henryyy");
		pilot.setLastName("Kewwwwwwwwwwl");
		pilot.setUserName("usssssssser");
		pilot.setPassword("secret");
		pilotService.addPilot(pilot);

		PilotLicense pilotLicense = new PilotLicense();
		pilotLicense.setAircraftType(aircraftType);
		pilotLicense.setPilot(pilot);
		pilotLicense.setValidFrom(new Date());
		pilotLicense.setValidThru(new Date());

		pilotLicenseService.addLicense(pilotLicense);

		Assert.assertNotNull(pilotLicense.getId());

		PilotLicense licenseFromDB = pilotLicenseService
				.getLicense(pilotLicense.getId());
		Assert.assertEquals(pilotLicense, licenseFromDB);

		Date newDate = new Date(new Date().getTime() + 10000);
		pilotLicense.setValidThru(newDate);
		pilotLicenseService.updateLicense(pilotLicense);
		Assert.assertEquals(pilotLicense,
				pilotLicenseService.getLicense(pilotLicense.getId()));

		pilotLicenseService.deleteLicense(pilotLicense.getId());
		Assert.assertNull(pilotLicenseService.getLicense(pilotLicense.getId()));

	}
}
