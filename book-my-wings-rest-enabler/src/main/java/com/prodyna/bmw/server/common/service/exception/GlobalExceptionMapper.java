package com.prodyna.bmw.server.common.service.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Henry Kuehl, PRODYNA AG
 * 
 */
@Provider
public class GlobalExceptionMapper implements ExceptionMapper<Exception> {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(GlobalExceptionMapper.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.ws.rs.ext.ExceptionMapper#toResponse(java.lang.Throwable)
	 */
	@Override
	public Response toResponse(Exception exception) {

		LOGGER.error("Caught ValidationException: {}", exception);

		return Response.status(Response.Status.BAD_REQUEST)
				.entity(exception.getLocalizedMessage())
				.type(MediaType.APPLICATION_JSON).build();
	}
}
