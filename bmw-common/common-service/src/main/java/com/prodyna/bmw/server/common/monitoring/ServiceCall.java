package com.prodyna.bmw.server.common.monitoring;

/**
 * @author Henry Kuehl, PRODYNA AG
 * 
 */
public class ServiceCall {

	public String service;

	public String method;

	public long executionTime;

	/**
	 * @param service
	 * @param method
	 * @param executionTime
	 */
	public ServiceCall(String service, String method, long executionTime) {
		super();
		this.service = service;
		this.method = method;
		this.executionTime = executionTime;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ServiceCall other = (ServiceCall) obj;
		if (method == null) {
			if (other.method != null) {
				return false;
			}
		} else if (!method.equals(other.method)) {
			return false;
		}
		if (service == null) {
			if (other.service != null) {
				return false;
			}
		} else if (!service.equals(other.service)) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((method == null) ? 0 : method.hashCode());
		result = prime * result + ((service == null) ? 0 : service.hashCode());
		return result;
	}

}
