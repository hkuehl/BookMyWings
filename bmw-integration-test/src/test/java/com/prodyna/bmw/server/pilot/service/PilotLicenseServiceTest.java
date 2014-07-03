package com.prodyna.bmw.server.pilot.service;

import java.util.Date;

import javax.inject.Inject;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.prodyna.bmw.server.aircraft.type.AircraftType;
import com.prodyna.bmw.server.license.PilotLicense;
import com.prodyna.bmw.server.license.PilotLicenseService;
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

	private AircraftType aircraftType = null;

	@Before
	public void before() {
		aircraftType = new AircraftType();
		aircraftType.setId(this.getClass().getName());
		aircraftType.setTypeString(this.getClass().getSimpleName());
	}

	@Test
	@InSequence(1)
	public void testCRUDPilotLicenseService() {

		PilotLicense pilotLicense = new PilotLicense();

		pilotLicense.setAircraftType(aircraftType);
		pilotLicense.setPilot(pilotService.getPilotById(this.getClass()
				.getName()));
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

	@Test
	@InSequence(2)
	public void testFindLicenseForPilotAndAircraftType() {
		PilotLicense pilotLicense = new PilotLicense();
		pilotLicense.setAircraftType(aircraftType);
		pilotLicense.setPilot(pilotService.getPilotById(this.getClass()
				.getName()));
		pilotLicense.setValidFrom(new Date());
		pilotLicense.setValidThru(new Date());

		pilotLicenseService.addLicense(pilotLicense);

		Assert.assertEquals(
				pilotLicense,
				pilotLicenseService.findLicenseForPilotAndAircraftType(
						this.getClass().getName(), aircraftType.getId()).get(0));
	}
}
