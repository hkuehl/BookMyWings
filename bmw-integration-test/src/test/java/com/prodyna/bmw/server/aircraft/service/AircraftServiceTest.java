package com.prodyna.bmw.server.aircraft.service;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.prodyna.bmw.server.aircraft.Aircraft;
import com.prodyna.bmw.server.aircraft.AircraftService;

/**
 * @author Henry Kuehl, PRODYNA AG
 */
@Ignore
@RunWith(Arquillian.class)
public class AircraftServiceTest {

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
	private AircraftService aircraftService;

	@Test
	public void testCreateAircraft() throws InterruptedException {

		Aircraft aircraft = new Aircraft();
		aircraft.setRegistration("D-EKF22");
		aircraftService.addAircraft(aircraft);
		Assert.assertTrue(aircraftService.getAircraft(aircraft.getId()) != null);
	}

}
