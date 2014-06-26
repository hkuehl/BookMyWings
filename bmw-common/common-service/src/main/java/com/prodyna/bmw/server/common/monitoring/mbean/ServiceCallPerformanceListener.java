package com.prodyna.bmw.server.common.monitoring.mbean;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Asynchronous;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.management.MBeanServer;
import javax.management.ObjectName;

import org.slf4j.Logger;

import com.prodyna.bmw.server.common.monitoring.ServiceCall;
import com.prodyna.bmw.server.common.monitoring.ServiceCallAggregatedData;

/**
 * @author Henry Kuehl, PRODYNA AG
 * 
 */
@Startup
@Singleton
public class ServiceCallPerformanceListener implements PerformanceMXBean {

	private static final Map<ServiceCall, ServiceCallAggregatedData> performanceData = new HashMap<>();

	private static final String OBJECT_NAME = "com.prodyna.bmw.server:service=Performance";

	@Asynchronous
	public static void receive(@Observes ServiceCall serviceCall)
			throws InterruptedException {
		ServiceCallAggregatedData serviceCallAggregatedData = performanceData
				.get(serviceCall);
		if (serviceCallAggregatedData == null) {
			serviceCallAggregatedData = new ServiceCallAggregatedData(
					serviceCall);
			performanceData.put(serviceCall, serviceCallAggregatedData);
		} else {
			serviceCallAggregatedData.add(serviceCall.executionTime);
		}

	}

	@Inject
	private MBeanServer mBeanServer;

	@Inject
	private Logger LOG;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.prodyna.bmw.server.common.monitoring.mbean.PerformanceMXBean#dump()
	 */
	@Override
	public void dump() {
		for (ServiceCallAggregatedData scad : performanceData.values()) {
			LOG.info(scad.toString());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.prodyna.bmw.server.common.monitoring.mbean.PerformanceMXBean#getCount
	 * ()
	 */
	@Override
	public long getCount() {
		return performanceData.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.prodyna.bmw.server.common.monitoring.mbean.PerformanceMXBean#
	 * getWorstByAverage()
	 */
	@Override
	public ServiceCallAggregatedData getWorstByAverage() {
		ServiceCallAggregatedData worst = null;
		for (ServiceCallAggregatedData pe : performanceData.values()) {
			if (worst == null) {
				worst = pe;
				continue;
			}
			if (pe.getAverage() > worst.getAverage()) {
				worst = pe;
			}
		}
		return worst;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.prodyna.bmw.server.common.monitoring.mbean.PerformanceMXBean#
	 * getWorstByCount()
	 */
	@Override
	public ServiceCallAggregatedData getWorstByCount() {
		ServiceCallAggregatedData worst = null;
		for (ServiceCallAggregatedData pe : performanceData.values()) {
			if (worst == null) {
				worst = pe;
				continue;
			}
			if (pe.getCount() > worst.getCount()) {
				worst = pe;
			}
		}
		return worst;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.prodyna.bmw.server.common.monitoring.mbean.PerformanceMXBean#
	 * getWorstBySum()
	 */
	@Override
	public ServiceCallAggregatedData getWorstBySum() {
		ServiceCallAggregatedData worst = null;
		for (ServiceCallAggregatedData pe : performanceData.values()) {
			if (worst == null) {
				worst = pe;
				continue;
			}
			if (pe.getSum() > worst.getSum()) {
				worst = pe;
			}
		}
		return worst;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.prodyna.bmw.server.common.monitoring.mbean.PerformanceMXBean#
	 * performanceForServiceAndMethod(java.lang.String, java.lang.String)
	 */
	@Override
	public ServiceCallAggregatedData performanceForServiceAndMethod(
			String service, String method) {
		return performanceData.get(new ServiceCall(service, method, 0));
	}

	@PostConstruct
	public void postConstruct() {
		try {
			mBeanServer.registerMBean(this, new ObjectName(OBJECT_NAME));
		} catch (Exception e) {
			throw new IllegalStateException("Could not register " + this
					+ " as " + OBJECT_NAME, e);
		}
	}

	@PreDestroy
	public void preDestory() {
		try {
			mBeanServer.unregisterMBean(new ObjectName(OBJECT_NAME));
		} catch (Exception e) {
			throw new IllegalStateException("Could not unregister "
					+ OBJECT_NAME, e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.prodyna.bmw.server.common.monitoring.mbean.PerformanceMXBean#reset()
	 */
	@Override
	public void reset() {
		performanceData.clear();
	}

}
