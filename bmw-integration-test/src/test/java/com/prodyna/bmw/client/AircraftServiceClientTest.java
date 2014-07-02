package com.prodyna.bmw.client;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.with;
//import static com.jayway.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.equalTo;

import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.jayway.restassured.http.ContentType;

/**
 * @author Henry Kuehl, PRODYNA AG
 * 
 */
@RunWith(Arquillian.class)
@Ignore
public class AircraftServiceClientTest {

	@Test
	@RunAsClient
	public void testCRUDAircraftClient() {

		with().parameters("id", "1", "registration", "D-HENK2")
				.contentType(ContentType.JSON)
				.when()
				.authentication()
				.basic("adminUser", "admin123")
				.post("http://127.0.0.1:9999/book-my-wings/rest/aircrafts/aircraft")
				.then().body("id", equalTo("1"));
		given().with()
				.contentType(ContentType.JSON)
				.get("http://127.0.0.1:9999/book-my-wings/rest/aircrafts/aircraft/1")
				.then().body("id", equalTo("1"));
	}
}
