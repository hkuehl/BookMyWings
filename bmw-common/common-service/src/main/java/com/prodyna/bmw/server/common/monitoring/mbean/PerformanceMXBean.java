package com.prodyna.bmw.server.common.monitoring.mbean;

import com.prodyna.bmw.server.common.monitoring.ServiceCallAggregatedData;

public interface PerformanceMXBean {
	void dump();

	long getCount();

	ServiceCallAggregatedData getWorstByAverage();

	ServiceCallAggregatedData getWorstByCount();

	ServiceCallAggregatedData getWorstBySum();

	ServiceCallAggregatedData performanceForServiceAndMethod(String service,
			String method);

	void reset();
}
