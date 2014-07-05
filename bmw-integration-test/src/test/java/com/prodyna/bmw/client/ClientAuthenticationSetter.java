package com.prodyna.bmw.client;

import java.io.IOException;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;

import org.codehaus.plexus.util.Base64;

public class ClientAuthenticationSetter implements ClientRequestFilter {

	private String auth;

	public ClientAuthenticationSetter(String username, String password) {
		new Base64();
		auth = "Basic "
				+ new String(Base64.encodeBase64(String.format("%s:%s",
						username, password).getBytes()));
	}

	@Override
	public void filter(ClientRequestContext clientRequestContext)
			throws IOException {
		clientRequestContext.getHeaders().add("Authorization", auth);
	}

}
