package com.prodyna.bmw.server.license;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.prodyna.bmw.server.common.monitoring.Monitored;
import com.prodyna.bmw.server.license.PilotLicense;
import com.prodyna.bmw.server.license.PilotLicenseService;

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
