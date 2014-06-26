package com.prodyna.bmw.server.common.monitoring.mbean;

public interface MonitoringMXBean {

	void disableLogging();

	void enableLogging();

	boolean isLoggingEnabled();
}
