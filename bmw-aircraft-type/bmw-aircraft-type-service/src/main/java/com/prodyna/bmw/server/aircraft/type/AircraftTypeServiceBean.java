package com.prodyna.bmw.server.aircraft.type;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.prodyna.bmw.server.common.monitoring.Monitored;

/**
 * @author Henry Kuehl, PRODYNA AG
 * 
 */
@Monitored
@Stateless
public class AircraftTypeServiceBean implements AircraftTypeService {

	@Inject
	private EntityManager em;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.prodyna.bmw.server.aircraft.type.AircraftTypeService#addAircraftType
	 * (com.prodyna.bmw.server.aircraft.type.AircraftType)
	 */
	@Override
	public AircraftType addAircraftType(AircraftType type) {
		em.persist(type);
		return type;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.prodyna.bmw.server.aircraft.type.AircraftTypeService#deleteAircraftType
	 * (java.lang.String)
	 */
	@Override
	public void deleteAircraftType(String id) {
		AircraftType type = em.find(AircraftType.class, id);
		em.remove(type);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.prodyna.bmw.server.aircraft.type.AircraftTypeService#getAircraftType
	 * (java.lang.String)
	 */
	@Override
	public AircraftType getAircraftType(String id) {
		return em.find(AircraftType.class, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.prodyna.bmw.server.aircraft.type.AircraftTypeService#updateAircraftType
	 * (com.prodyna.bmw.server.aircraft.type.AircraftType)
	 */
	@Override
	public AircraftType updateAircraftType(AircraftType type) {
		AircraftType mergedType = em.merge(type);
		return mergedType;
	}

}
