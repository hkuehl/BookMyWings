package com.prodyna.bmw.server.aircraft;

/**
 * @author Henry Kuehl, PRODYNA AG
 */
public interface AircraftService {

	Aircraft addAircraft(Aircraft aircraft);

	void deleteAircraft(String uuid);

	Aircraft getAircraft(String uuid);

	void updateAircraft(Aircraft aircraft);

}
