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

		// File[] mpc = Maven.resolver().loadPomFromFile("pom.xml")
		// .importCompileAndRuntimeDependencies().resolve()
		// .withTransitivity().asFile();
		// for (File f : mpc) {
		// System.out.println("Found dependency: " + f);
		// }

		WebArchive wa = ShrinkWrap.create(WebArchive.class, "test.war");
		wa.addPackages(true, "com.prodyna.bmw.server");
		wa.addAsResource("META-INF/persistence.xml");
		wa.addAsResource("META-INF/beans.xml");
		// wa.addAsLibraries(mpc);
		System.out.println(wa.toString(true));
		return wa;
	}
}
