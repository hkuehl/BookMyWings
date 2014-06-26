package com.prodyna.bmw.server.pilot.service;

import javax.inject.Inject;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.prodyna.bmw.server.pilot.Pilot;
import com.prodyna.bmw.server.pilot.PilotService;

/**
 * @author Henry Kuehl, PRODYNA AG
 * 
 */
@RunWith(Arquillian.class)
public class PilotServiceTest {

	@Inject
	private PilotService pilotService;

	@Test
	public void testCRUDPilotService() {

		Pilot pilot = new Pilot();
		pilot.setFirstName("Alexander");
		pilot.setLastName("Marcus");

		pilotService.addPilot(pilot);

		// uuid generated?
		Assert.assertNotNull(pilot.getId());

		// read
		Assert.assertEquals(pilot, pilotService.getPilot(pilot.getId()));

		// update
		pilot.setFirstName("Henry");
		pilotService.updatePilot(pilot);
		Assert.assertEquals("Henry", pilotService.getPilot(pilot.getId())
				.getFirstName());

		// delete
		pilotService.deletePilot(pilot.getId());
		Assert.assertNull(pilotService.getPilot(pilot.getId()));

	}
}
