package com.prodyna.bmw.server.license;

import java.util.List;

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
@Path("/pilots/licenses")
@Produces("application/json")
@Consumes("application/json")
public interface PilotLicenseService {

	@RolesAllowed("admin")
	@POST
	@Path("/license")
	PilotLicense addLicense(PilotLicense license);

	@DELETE
	@RolesAllowed("admin")
	@Path("/license/{id}")
	void deleteLicense(@PathParam("id") String id);

	@GET
	@Path("/license/pilot/{pilotId}/aircraftType/{aircraftTypeId}")
	List<PilotLicense> findLicenseForPilotAndAircraftType(
			@PathParam("pilotId") String pilotId,
			@PathParam("aircraftTypeId") String aircraftTypeId);

	@GET
	@Path("/license/{id}")
	PilotLicense getLicense(@PathParam("id") String id);

	@PUT
	@Path("/license")
	@RolesAllowed("admin")
	PilotLicense updateLicense(PilotLicense license);

}
