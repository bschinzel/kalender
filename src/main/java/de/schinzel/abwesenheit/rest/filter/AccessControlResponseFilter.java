package de.schinzel.abwesenheit.rest.filter;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;

public class AccessControlResponseFilter implements ContainerResponseFilter {
	
	private static final String WEBSERVER_URL = System.getenv("WEBSERVER_URL");

	@Override
	public void filter(ContainerRequestContext requestCtx, ContainerResponseContext responseCtx) 
			throws IOException {
		
		final MultivaluedMap<String, Object> headers = responseCtx.getHeaders();
		
		headers.add("Access-Control-Allow-Origin", WEBSERVER_URL);
		headers.add("Vary", "Origin");
	}

}
