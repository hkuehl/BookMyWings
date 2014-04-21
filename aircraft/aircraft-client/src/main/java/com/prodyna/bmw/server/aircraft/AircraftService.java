package com.prodyna.bmw.server.aircraft;

import java.util.List;

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

/**
 * @author Henry Kuehl, PRODYNA AG
 */
@Local
@Path("/aircraft")
@Produces("application/json")
@Consumes("application/json")
public interface AircraftService {

	@POST
	Aircraft addAircraft(Aircraft aircraft);

	@DELETE
	@Path("{aircraftId}")
	void deleteAircraft(@PathParam("aircraftId") String uuid);

	@GET
	@Path("{aircraftId}")
	Aircraft getAircraft(@PathParam("aircraftId") String uuid);

	@GET
	List<Aircraft> readAllAircrafts(@QueryParam("start") @Min(0) Integer start,
			@QueryParam("pageSize") @Max(1000) Integer pageSize);

	@PUT
	void updateAircraft(Aircraft aircraft);

}
