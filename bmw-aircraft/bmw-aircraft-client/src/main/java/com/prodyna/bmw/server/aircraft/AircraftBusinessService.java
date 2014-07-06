package com.prodyna.bmw.server.aircraft;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Local;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * @author Henry Kuehl, PRODYNA AG
 * 
 */
@Local
@Path("/aircrafts/business")
@Produces("application/json")
@Consumes("application/json")
public interface AircraftBusinessService {

	@GET
	@RolesAllowed({ "admin", "user" })
	@Path("/pilot/{pilotId}")
	List<Aircraft> readAircraftsForPilot(@PathParam("pilotId") String pilotId);

}
