package com.prodyna.bmw.server.aircraft.service;

import javax.inject.Inject;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.prodyna.bmw.server.aircraft.AircraftBusinessService;

/**
 * @author Henry Kuehl, PRODYNA AG
 * 
 */
@RunWith(Arquillian.class)
public class AircraftBusinessServiceTest {

	@Inject
	private AircraftBusinessService aircraftBusinessService;

	@Test
	public void testFindAircraftsForPilot() {
		Assert.assertEquals(this.getClass().getName(), aircraftBusinessService
				.readAircraftsForPilot("pilotId").get(0).getRegistration());
	}
}
