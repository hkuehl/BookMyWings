package com.prodyna.bmw.server.rest;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

/**
 * @author Henry Kuehl, PRODYNA AG
 * 
 */
@Provider
public class CORSFilter implements ContainerResponseFilter {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.ws.rs.container.ContainerResponseFilter#filter(javax.ws.rs.container
	 * .ContainerRequestContext, javax.ws.rs.container.ContainerResponseContext)
	 */
	@Override
	public void filter(ContainerRequestContext requestContext,
			ContainerResponseContext responseContext) throws IOException {
		responseContext.getHeaders().add("Access-Control-Allow-Origin", "*");
		responseContext.getHeaders().add("Access-Control-Allow-Headers",
				"origin, content-type, accept, authorization");
		responseContext.getHeaders().add("Access-Control-Allow-Credentials",
				"true");
		responseContext.getHeaders().add("Access-Control-Allow-Methods",
				"GET, POST, PUT, DELETE, OPTIONS, HEAD");
		responseContext.getHeaders().add("Access-Control-Max-Age", "1209600");

	}

}
