package com.prodyna.bmw.server.pilot.service;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
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

	@Deployment
	public static WebArchive createDeployment() {
		WebArchive wa = ShrinkWrap.create(WebArchive.class, "test.war");
		wa.addPackages(true, "com.prodyna.bmw.server");
		wa.addAsResource("META-INF/persistence.xml");
		wa.addAsResource("META-INF/beans.xml");
		System.out.println(wa.toString(true));
		return wa;
	}

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
