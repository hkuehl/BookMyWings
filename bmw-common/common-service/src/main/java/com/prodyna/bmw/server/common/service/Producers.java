package com.prodyna.bmw.server.common.service;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Henry Kuehl, PRODYNA AG
 * 
 */
public class Producers {

	@PersistenceContext
	private EntityManager entityManager;

	@Produces
	public EntityManager produceEntityManager() {
		return entityManager;
	}

	@Produces
	public Logger produceLogger(InjectionPoint ip) {
		return LoggerFactory.getLogger(ip.getMember().getDeclaringClass()
				.getClass());
	}

}
