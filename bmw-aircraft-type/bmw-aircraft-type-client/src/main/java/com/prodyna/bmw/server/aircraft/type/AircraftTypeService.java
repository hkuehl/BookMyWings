package com.prodyna.bmw.server.aircraft.type;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Local;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * @author Henry Kuehl, PRODYNA AG
 * 
 */
@Local
@Path("/aircrafts/types")
@Produces("application/json")
@Consumes("application/json")
public interface AircraftTypeService {

	@Path("/type")
	@POST
	@RolesAllowed("admin")
	AircraftType addAircraftType(AircraftType type);

	@DELETE
	@RolesAllowed("admin")
	@Path("/type/{typeId}")
	void deleteAircraftType(@PathParam("typeId") String id);

	@GET
	@Path("/type/{typeId}")
	AircraftType getAircraftType(@PathParam("typeId") String id);

	@PUT
	@Path("/type")
	@RolesAllowed("admin")
	AircraftType updateAircraftType(AircraftType type);

}
