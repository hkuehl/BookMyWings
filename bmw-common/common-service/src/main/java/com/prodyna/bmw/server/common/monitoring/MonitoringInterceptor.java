package com.prodyna.bmw.server.common.monitoring;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.logging.Logger;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@Monitored
public class MonitoringInterceptor {

	public static boolean isLogEnabled() {
		return logEnabled;
	}

	public static void setLogEnabled(final boolean logEnabled) {
		MonitoringInterceptor.logEnabled = logEnabled;
	}

	@Inject
	private Event<ServiceCall> serviceCallEvent;

	private static final String WELD = "$Proxy$";

	private static boolean logEnabled = true;

	@AroundInvoke
	public Object log(final InvocationContext context) throws Exception {
		String target = context.getTarget().getClass().getName();
		if (target.contains(WELD)) {
			int i = target.indexOf(WELD);
			target = target.substring(0, i);
		}
		Logger log = Logger.getLogger(target);
		Method m = context.getMethod();
		boolean v = m.getReturnType().equals(Void.TYPE);
		String line = m.getName() + params(context);
		if (logEnabled) {
			log.info(">>> " + line);
		}
		long before = System.currentTimeMillis();
		Object ret;
		try {
			ret = context.proceed();
		} catch (Exception e) {
			if (logEnabled) {
				log.info("<<< " + line + " ! " + e.getClass().getName());
			}
			throw e;
		}
		long after = System.currentTimeMillis();
		long diff = after - before;
		String retout = "" + ret;
		if (retout.length() > 200) {
			retout = retout.substring(0, 200) + "...";
		}
		if (logEnabled) {
			log.info("<<< " + line + " {" + diff + "} " + (v ? "" : retout));
		}

		ServiceCall serviceCall = new ServiceCall(target, m.getName(), diff);
		serviceCallEvent.fire(serviceCall);

		return ret;
	}

	private String params(final InvocationContext context) {
		StringBuffer sb = new StringBuffer();

		Arrays.asList(context.getParameters()).stream().forEach(parameter -> {
			if (sb.length() > 0) {
				sb.append(",");
			}
			sb.append(parameter == null ? "null" : parameter.toString());
		});

		String out = sb.toString();
		if (out.length() > 200) {
			out = sb.substring(0, 199) + "...";
		}
		return "(" + out + ")";
	}

}
