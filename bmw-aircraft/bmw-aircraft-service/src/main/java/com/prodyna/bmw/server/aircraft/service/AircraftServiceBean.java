package com.prodyna.bmw.server.aircraft.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.prodyna.bmw.server.aircraft.Aircraft;
import com.prodyna.bmw.server.aircraft.AircraftService;
import com.prodyna.bmw.server.common.monitoring.Monitored;

/**
 * @author Henry Kuehl, PRODYNA AG
 * 
 */
@Stateless
@Monitored
public class AircraftServiceBean implements AircraftService {

	@Inject
	private EntityManager entityManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.prodyna.bmw.server.aircraft.AircraftService#addAircraft(com.prodyna
	 * .bmw.server.aircraft.Aircraft)
	 */
	@Override
	public Aircraft addAircraft(Aircraft aircraft) {
		entityManager.persist(aircraft);
		return aircraft;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.prodyna.bmw.server.aircraft.AircraftService#deleteAircraft(java.lang
	 * .String)
	 */
	@Override
	public void deleteAircraft(String uuid) {
		Aircraft aircraft = entityManager.find(Aircraft.class, uuid);
		entityManager.remove(aircraft);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.prodyna.bmw.server.aircraft.AircraftService#getAircraft(java.lang
	 * .String)
	 */
	@Override
	public Aircraft getAircraft(String uuid) {
		return entityManager.find(Aircraft.class, uuid);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.prodyna.bmw.server.aircraft.AircraftService#getAircraftsForType(java
	 * .lang.String)
	 */
	@Override
	public List<Aircraft> getAircraftsForType(String aircraftTypeId) {
		return entityManager
				.createNamedQuery(Aircraft.QUERY_GET_AIRCRAFT_BY_TYPE,
						Aircraft.class)
				.setParameter(Aircraft.QUERY_PARM_AIRCRAFT_TYPE, aircraftTypeId)
				.getResultList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.prodyna.bmw.server.aircraft.AircraftService#readAllAircrafts(java
	 * .lang.Long, java.lang.Long)
	 */
	@Override
	public List<Aircraft> readAllAircrafts(Integer start, Integer pageSize) {
		return entityManager
				.createNamedQuery(Aircraft.QUERY_GET_ALL_AIRCRAFTS_PAGINATED,
						Aircraft.class).setFirstResult(start)
				.setMaxResults(pageSize).getResultList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.prodyna.bmw.server.aircraft.AircraftService#updateAircraft(java.lang
	 * .String)
	 */
	@Override
	public void updateAircraft(Aircraft aircraft) {
		entityManager.merge(aircraft);
	}
}
