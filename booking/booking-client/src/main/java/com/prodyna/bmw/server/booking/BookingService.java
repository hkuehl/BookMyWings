package com.prodyna.bmw.server.booking;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Local;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.prodyna.bmw.server.aircraft.Aircraft;

/**
 * @author Henry Kuehl, PRODYNA AG
 */
@Local
@Path("/bookings")
@Produces("application/json")
@Consumes("application/json")
@PermitAll
public interface BookingService {

	@POST
	@RolesAllowed("Manager")
	Booking addBooking(Booking booking);

	@DELETE
	@RolesAllowed("Manager")
	@Path("{bookingId}")
	void deleteBooking(@PathParam("bookingId") String bookingId);

	@Path("/aircrafts/pilot")
	@GET
	@RolesAllowed("Manager")
	@PathParam("{pilotId}")
	List<Aircraft> findAircraftsForPilot(@PathParam("{pilotId}") String pilotId);

	@GET
	List<Booking> readAllBookings(@QueryParam("start") @Min(0) Integer start,
			@QueryParam("pageSize") @Min(1) @Max(1000) Integer pageSize);

	@GET
	@Path("/booking/{bookingId}")
	Booking readBookingForId(@PathParam("bookingId") String bookingId);

	@PUT
	@RolesAllowed("Manager")
	void updateBooking(Booking booking);

}
