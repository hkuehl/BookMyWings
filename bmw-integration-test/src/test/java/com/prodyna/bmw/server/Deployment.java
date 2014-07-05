package com.prodyna.bmw.server;

import org.eu.ingwar.tools.arquillian.extension.suite.annotations.ArquilianSuiteDeployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;

/**
 * @author Henry Kuehl, PRODYNA AG
 * 
 */
@ArquilianSuiteDeployment
public class Deployment {

	@org.jboss.arquillian.container.test.api.Deployment
	public static WebArchive createDeployment() throws InterruptedException {

		WebArchive war = ShrinkWrap.create(WebArchive.class, "test.war");
		war.addPackages(true, "com.prodyna.bmw");
		war.addPackages(true, "org.apache.commons.collections");
		war.addAsResource("META-INF/persistence.xml");
		war.addAsResource("META-INF/beans.xml");

		war.addAsWebInfResource("WEB-INF/jboss-web.xml");
		war.addAsWebInfResource("WEB-INF/web.xml");
		return war;

	}
}
