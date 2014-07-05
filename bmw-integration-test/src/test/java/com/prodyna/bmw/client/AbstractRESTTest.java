package com.prodyna.bmw.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.jersey.client.proxy.WebResourceFactory;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.jsonp.JsonProcessingFeature;
import org.jboss.arquillian.test.api.ArquillianResource;

public class AbstractRESTTest {

	public static final String USERNAME = "username";
	public static final String PASSWORD = "secret";

	@ArquillianResource
	protected java.net.URL url;

	protected Client createClient(boolean withAuth) {
		final Client client = ClientBuilder.newClient();
		client.register(JsonProcessingFeature.class); // needed for "normal"
														// clients
		client.register(JacksonFeature.class); // needed for dynamic proxy
		if (withAuth) {
			client.register(new ClientAuthenticationSetter(USERNAME, PASSWORD));
		}
		return client;
	}

	protected <C> C createService(Class<C> ifaceType) {
		return WebResourceFactory.newResource(ifaceType, createWebTarget());
	}

	protected WebTarget createWebTarget() {
		WebTarget target = createClient(true).target(url.toString() + "rest");
		return target;
	}

	protected WebTarget createWebTargetNoAuth() {
		WebTarget target = createClient(false).target(url.toString() + "rest");
		return target;
	}

}