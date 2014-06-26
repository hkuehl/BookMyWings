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
	public static WebArchive createDeployment() {
		WebArchive wa = ShrinkWrap.create(WebArchive.class, "test.war");
		wa.addPackages(true, "com.prodyna.bmw.server");
		wa.addAsResource("META-INF/persistence.xml");
		wa.addAsResource("META-INF/beans.xml");
		System.out.println(wa.toString(true));
		return wa;
	}
}
