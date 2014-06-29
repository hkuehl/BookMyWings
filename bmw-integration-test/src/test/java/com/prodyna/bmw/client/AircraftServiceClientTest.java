package com.prodyna.bmw.client;

import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.prodyna.bmw.server.aircraft.Aircraft;
import com.prodyna.bmw.server.aircraft.AircraftService;

/**
 * @author Henry Kuehl, PRODYNA AG
 * 
 */
@Ignore
@RunWith(Arquillian.class)
public class AircraftServiceClientTest extends AbstractRESTTest {

	@RunAsClient
	@Test
	public void testCreateAircraft() {
		AircraftService aircraftService = createService(AircraftService.class);
		Aircraft aircraft = new Aircraft();
		aircraft.setRegistration("D-EFGH");
		aircraftService.addAircraft(aircraft);
	}
}
