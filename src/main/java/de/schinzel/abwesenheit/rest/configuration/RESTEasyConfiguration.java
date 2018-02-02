package de.schinzel.abwesenheit.rest.configuration;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.jboss.logging.Logger;

@ApplicationPath("/abwesenheiten")
public class RESTEasyConfiguration extends Application {
	
	private static final Logger LOGGER = Logger.getLogger(RESTEasyConfiguration.class);
	
	static {
		LOGGER.infof("Es wurde die Konfiguration zum Projectstage '%s' geladen", System.getProperty("project.stage"));
	}
	
	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> s = new HashSet<Class<?>>();
		s.add(de.schinzel.abwesenheit.rest.v1.AbwesenheitEndpoint.class);
		s.add(de.schinzel.abwesenheit.rest.exceptionmapper.EJBTransactionRolledbackExceptionMapper.class);
		s.add(de.schinzel.abwesenheit.rest.exceptionmapper.EJBExceptionMapper.class);
		s.add(de.schinzel.abwesenheit.rest.filter.AccessControlResponseFilter.class);
		return s;
	}

	@Override
	public Set<Object> getSingletons() {
		Set<Object> singletons = super.getSingletons();
		return singletons;
	}
}
