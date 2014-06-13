package com.prodyna.bmw.client;

import static com.jayway.restassured.RestAssured.given;

import org.junit.Test;

import com.jayway.restassured.http.ContentType;

/**
 * @author Henry Kuehl, PRODYNA AG
 * 
 */
public class AircraftClientTest {

	@Test
	public void createAircraft() {
		System.out.println(given().auth().basic("test", "test")
				.body("{\"registration\":\"D-TFVBHGFG\"}")
				.contentType(ContentType.JSON).when()
				.post("/book-my-wings/rest/aircrafts").body().asString());
	}
}
