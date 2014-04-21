package com.prodyna.bmw.server.aircraft.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.slf4j.Logger;

import com.prodyna.bmw.server.aircraft.Aircraft;
import com.prodyna.bmw.server.aircraft.AircraftService;

/**
 * @author Henry Kuehl, PRODYNA AG
 * 
 */
@Stateless
public class AircraftServiceBean implements AircraftService {

	@Inject
	private Logger LOGGER;

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
		LOGGER.info("Aircraft created {}", aircraft.getId());
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
		LOGGER.info("Deleted aircraft {}", uuid);
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
		LOGGER.info("get Aircraft called with id {}", uuid);
		return entityManager.find(Aircraft.class, uuid);
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
		LOGGER.info("Updated aircraft {}", aircraft.getId());
	}
}
