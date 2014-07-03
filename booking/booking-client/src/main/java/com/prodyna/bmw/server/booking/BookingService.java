package com.prodyna.bmw.server.booking;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Local;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 * @author Henry Kuehl, PRODYNA AG
 */
@Local
@Path("/bookings")
@Produces("application/json")
@Consumes("application/json")
public interface BookingService extends BookingStateService {

	@POST
	@RolesAllowed({ "user", "admin" })
	@Path("/booking")
	Booking addBooking(@Valid Booking booking);

	@DELETE
	@RolesAllowed("admin")
	@Path("{bookingId}")
	void deleteBooking(@PathParam("bookingId") String bookingId);

	@GET
	List<Booking> readAllBookings(@QueryParam("start") @Min(0) Integer start,
			@QueryParam("pageSize") @Min(1) @Max(1000) Integer pageSize);

	@GET
	@RolesAllowed({ "user", "admin" })
	@Path("/booking/{bookingId}")
	Booking readBookingForId(@PathParam("bookingId") String bookingId);

	@GET
	@Path("/filter/month/{month}")
	List<Booking> readBookingsForMonth(
			@PathParam("month") @Min(1) @Max(12) Integer month);

	@GET
	@RolesAllowed({ "user", "admin" })
	@Path("/filter/pilot/{pilotId}")
	List<Booking> readBookingsForPilot(
			@PathParam("pilotId") @NotNull String pilotId);

	@PUT
	@RolesAllowed("admin")
	void updateBooking(@Valid Booking booking);

}
