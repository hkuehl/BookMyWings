package com.prodyna.bmw.server.license;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.prodyna.bmw.server.common.monitoring.Monitored;

/**
 * @author Henry Kuehl, PRODYNA AG
 * 
 */
@Stateless
@Monitored
public class PilotLicenseServiceBean implements PilotLicenseService {

	@Inject
	private EntityManager em;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.prodyna.bmw.server.license.PilotLicenseService#addLicense(com.prodyna
	 * .bmw.server.license.PilotLicense)
	 */
	@Override
	public PilotLicense addLicense(PilotLicense license) {
		em.persist(license);
		return license;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.prodyna.bmw.server.license.PilotLicenseService#deleteLicense(java
	 * .lang.String)
	 */
	@Override
	public void deleteLicense(String id) {
		PilotLicense license = em.find(PilotLicense.class, id);
		em.remove(license);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.prodyna.bmw.server.license.PilotLicenseService#
	 * findLicenseForPilotAndAircraftType (java.lang.String)
	 */
	@Override
	public List<PilotLicense> findLicenseForPilotAndAircraftType(
			String pilotId, String aircraftTypeId) {
		return em
				.createNamedQuery(PilotLicense.QUERY_FIND_LICENSE_FOR_PILOT,
						PilotLicense.class)
				.setParameter(PilotLicense.QUERY_PARM_PILOT, pilotId)
				.setParameter(PilotLicense.QUERY_PARM_AIRCRAFTTYPE,
						aircraftTypeId).getResultList();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.prodyna.bmw.server.license.PilotLicenseService#getLicense(java.lang
	 * .String)
	 */
	@Override
	public PilotLicense getLicense(String id) {
		return em.find(PilotLicense.class, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.prodyna.bmw.server.license.PilotLicenseService#updateLicense(com.
	 * prodyna.bmw.server.license.PilotLicense)
	 */
	@Override
	public PilotLicense updateLicense(PilotLicense license) {
		return em.merge(license);
	}

}
