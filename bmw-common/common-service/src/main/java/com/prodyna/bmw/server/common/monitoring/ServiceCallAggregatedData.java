package com.prodyna.bmw.server.common.monitoring;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ServiceCallAggregatedData {

	private String service;
	private String method;
	private Long min = null;
	private Long max = null;
	private long sum = 0;
	private long count = 0;

	public ServiceCallAggregatedData(final ServiceCall serviceCall) {
		this.service = serviceCall.service;
		this.method = serviceCall.method;
		add(serviceCall.executionTime);
	}

	public void add(final long execTime) {
		if (min == null) {
			min = execTime;
		}
		if (max == null) {
			max = execTime;
		}
		if (execTime < min) {
			min = execTime;
		}
		if (execTime > max) {
			max = execTime;
		}
		sum += execTime;
		count++;
	}

	public long getAverage() {
		return (long) ((float) sum / (float) count);
	}

	public long getCount() {
		return count;
	}

	public long getMax() {
		return max;
	}

	public long getMedian() {
		return (long) ((float) sum / (float) count);
	}

	public String getMethod() {
		return method;
	}

	public long getMin() {
		return min;
	}

	public String getService() {
		return service;
	}

	public long getSum() {
		return sum;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}
}
