package com.prodyna.bmw.server.common.monitoring.mbean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.management.MBeanServer;
import javax.management.ObjectName;

import com.prodyna.bmw.server.common.monitoring.MonitoringInterceptor;

@Startup
@Singleton
public class MBeanServerRegistrationSingleton implements MonitoringMXBean {

	@Inject
	private MBeanServer ms;

	private static final String OBJECT_NAME = "com.prodyna.bmw.server:service=MonitoringSwitch";

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.prodyna.bmw.server.common.monitoring.mbean.MonitoringMXBean#
	 * disableLogging()
	 */
	@Override
	public void disableLogging() {
		MonitoringInterceptor.setLogEnabled(false);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.prodyna.bmw.server.common.monitoring.mbean.MonitoringMXBean#enableLogging
	 * ()
	 */
	@Override
	public void enableLogging() {
		MonitoringInterceptor.setLogEnabled(true);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.prodyna.bmw.server.common.monitoring.mbean.MonitoringMXBean#
	 * isLoggingEnabled()
	 */
	@Override
	public boolean isLoggingEnabled() {
		return MonitoringInterceptor.isLogEnabled();
	}

	@PostConstruct
	public void postConstruct() {
		try {
			ms.registerMBean(this, new ObjectName(OBJECT_NAME));
		} catch (Exception e) {
			throw new IllegalStateException("Could not register " + this
					+ " as " + OBJECT_NAME, e);
		}
	}

	@PreDestroy
	public void preDestory() {
		try {
			ms.unregisterMBean(new ObjectName(OBJECT_NAME));
		} catch (Exception e) {
			throw new IllegalStateException("Could not unregister "
					+ OBJECT_NAME, e);
		}
	}

}
