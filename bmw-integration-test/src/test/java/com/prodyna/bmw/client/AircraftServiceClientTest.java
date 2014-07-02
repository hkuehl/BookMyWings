package com.prodyna.bmw.client;

import static com.jayway.restassured.RestAssured.with;
//import static com.jayway.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Ignore;
import org.junit.Test;

import com.jayway.restassured.http.ContentType;

/**
 * @author Henry Kuehl, PRODYNA AG
 * 
 */
@Ignore
public class AircraftServiceClientTest {

	@Test
	public void testCRUDAircraftClient() {

		with().parameters("id", "1", "registration", "D-HENK2")
				.contentType(ContentType.JSON)
				.when()
				.authentication()
				.basic("adminUser", "admin123")
				.post("http://localhost:8080/book-my-wings/rest/aircrafts/aircraft")
				.then().body("id", equalTo("1"));

		// get("http://localhost:8080/book-my-wings/rest/aircrafts/aircraft/1")
		// .then().body("id", equalTo("1"));
	}
}
