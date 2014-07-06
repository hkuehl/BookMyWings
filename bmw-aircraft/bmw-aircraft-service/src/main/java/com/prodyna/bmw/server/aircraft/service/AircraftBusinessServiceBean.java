package com.prodyna.bmw.server.aircraft.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.prodyna.bmw.server.aircraft.Aircraft;
import com.prodyna.bmw.server.aircraft.AircraftBusinessService;
import com.prodyna.bmw.server.aircraft.AircraftService;
import com.prodyna.bmw.server.common.monitoring.Monitored;
import com.prodyna.bmw.server.license.PilotLicense;
import com.prodyna.bmw.server.license.PilotLicenseService;

/**
 * @author Henry Kuehl, PRODYNA AG
 *
 */
@Stateless
@Monitored
public class AircraftBusinessServiceBean implements AircraftBusinessService {

	@Inject
	private PilotLicenseService pilotLicenseService;

	@Inject
	private AircraftService aircraftService;

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * com.prodyna.bmw.server.aircraft.AircraftBusinessService#readAircraftsForPilot
	 * (java.lang.String)
	 */
	@Override
	public List<Aircraft> readAircraftsForPilot(String pilotId) {
		List<PilotLicense> licnesesForPilot = pilotLicenseService
				.readLicensesForPilot(pilotId);

		final List<Aircraft> aircrafts = new ArrayList<Aircraft>();

		licnesesForPilot.stream().forEach(
				(pilotLicense) -> {
					List<Aircraft> aircraftsForType = aircraftService
							.getAircraftsForType(pilotLicense.getAircraftType()
									.getId());
					aircrafts.addAll(aircraftsForType);
				});

		return aircrafts;
	}

}
