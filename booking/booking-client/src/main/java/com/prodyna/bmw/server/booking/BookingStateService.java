package com.prodyna.bmw.server.booking;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 * @author Henry Kuehl, PRODYNA AG
 * 
 */
public interface BookingStateService {

	@PUT
	@Path("/booking/bringBack/{bookingId}")
	@RolesAllowed("admin")
	Booking bringBack(@PathParam("bookingId") String bookingId);

	@PUT
	@Path("/booking/cancel/{bookingId}")
	@RolesAllowed("admin")
	Booking cancel(@PathParam("bookingId") String bookingId);

	@GET
	@Path("/booking/transitions/{bookingId}")
	List<BookingState> allowedTransisitons(
			@PathParam("bookingId") String bookingId);

	@PUT
	@Path("/booking/reserve/{bookingId}")
	@RolesAllowed("admin")
	Booking reserve(@PathParam("bookingId") String bookingId);

	@PUT
	@Path("/booking/retrieve/{bookingId}")
	@RolesAllowed("admin")
	Booking retrieve(@PathParam("bookingId") String bookingId);

	@PUT
	@Path("/booking/transfer/{bookingId}/{bookingState}")
	@RolesAllowed("admin")
	Booking setState(@PathParam("bookingId") String bookingId,
			@PathParam("bookingState") String bookingState);

}
