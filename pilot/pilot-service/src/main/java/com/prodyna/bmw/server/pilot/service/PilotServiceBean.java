package com.prodyna.bmw.server.pilot.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.slf4j.Logger;

import com.prodyna.bmw.server.pilot.Pilot;
import com.prodyna.bmw.server.pilot.PilotService;

/**
 * @author Henry Kuehl, PRODYNA AG
 * 
 */
@Stateless
public class PilotServiceBean implements PilotService {

	@Inject
	private EntityManager em;

	@Inject
	private Logger LOG;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.prodyna.bmw.server.pilot.PilotService#addPilot(com.prodyna.bmw.server
	 * .pilot.Pilot)
	 */
	@Override
	public Pilot addPilot(Pilot pilot) {
		em.persist(pilot);
		LOG.info("Persisted Pilot with id {}", pilot.getId());
		return pilot;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.prodyna.bmw.server.pilot.PilotService#deletePilot(java.lang.String)
	 */
	@Override
	public void deletePilot(String uuid) {
		Pilot pilot = em.find(Pilot.class, uuid);
		em.remove(pilot);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.prodyna.bmw.server.pilot.PilotService#getPilot(java.lang.String)
	 */
	@Override
	public Pilot getPilot(String uuid) {
		return em.find(Pilot.class, uuid);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.prodyna.bmw.server.pilot.PilotService#readAllPilots(java.lang.Integer
	 * , java.lang.Integer)
	 */
	@Override
	public List<Pilot> readAllPilots(Integer start, Integer pageSize) {
		return em
				.createNamedQuery(Pilot.QUERY_GET_ALL_PILOTS_PAGINATED,
						Pilot.class).setFirstResult(start)
				.setMaxResults(pageSize).getResultList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.prodyna.bmw.server.pilot.PilotService#updatePilot(com.prodyna.bmw
	 * .server.pilot.Pilot)
	 */
	@Override
	public void updatePilot(Pilot pilot) {
		em.merge(pilot);
	}

}
