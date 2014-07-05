package com.prodyna.bmw.client;

import java.io.UnsupportedEncodingException;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.prodyna.bmw.server.aircraft.Aircraft;
import com.prodyna.bmw.server.aircraft.AircraftService;

@RunWith(Arquillian.class)
public class AircraftServiceClientTest extends AbstractRESTTest {

	@Test
	@RunAsClient
	public void testClientAsDynamicProxy() {
		AircraftService aircraftService = createService(AircraftService.class);
		Aircraft aircraft = aircraftService.getAircraft("aircraftId");
		Assert.assertEquals("aircraftId", aircraft.getId());
	}

	@Test
	@RunAsClient
	public void testClientAsRest() throws InterruptedException,
			UnsupportedEncodingException {
		WebTarget target = createWebTarget();
		JsonObject aircraft = target.path("/aircrafts/aircraft/aircraftId")
				.request(MediaType.APPLICATION_JSON_TYPE).get(JsonObject.class);

		Assert.assertEquals("\"aircraftId\"", aircraft.get("id").toString());

		JsonArray aircrafts = target
				.path("/aircrafts/aircraft/type/aircraftTypeId")
				.request(MediaType.APPLICATION_JSON_TYPE).get(JsonArray.class);
		Assert.assertEquals(
				"\"com.prodyna.bmw.server.aircraft.service.AircraftBusinessServiceTest\"",
				aircrafts.getJsonObject(0).get("registration").toString());

	}

	@Test(expected = BadRequestException.class)
	@RunAsClient
	public void testClientAuthentication() {
		WebTarget target = createWebTargetNoAuth();
		target.path("/aircrafts/aircraft/type/aircraftTypeId")
				.request(MediaType.APPLICATION_JSON_TYPE).get(JsonObject.class);
	}

}
