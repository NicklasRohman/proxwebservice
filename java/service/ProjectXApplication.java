package service;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationPath("")
public class ProjectXApplication extends Application {
	@Produces
	public Logger getLogger(InjectionPoint ip) {
		return LoggerFactory.getLogger(ip.getBean().getBeanClass());
	}
}
