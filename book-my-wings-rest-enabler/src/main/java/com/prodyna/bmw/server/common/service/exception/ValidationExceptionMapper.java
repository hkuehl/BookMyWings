package com.prodyna.bmw.server.common.service.exception;

import javax.validation.ValidationException;
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
public class ValidationExceptionMapper implements
		ExceptionMapper<ValidationException> {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ValidationExceptionMapper.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.ws.rs.ext.ExceptionMapper#toResponse(java.lang.Throwable)
	 */
	@Override
	public Response toResponse(ValidationException exception) {

		LOGGER.error("Caught ValidationException: {}", exception);

		return Response.status(Response.Status.PRECONDITION_FAILED)
				.entity(exception.getLocalizedMessage())
				.type(MediaType.TEXT_PLAIN_TYPE).build();
	}
}
