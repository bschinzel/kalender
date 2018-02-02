package de.schinzel.abwesenheit.rest.exceptionmapper;

import javax.ejb.EJBException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class EJBExceptionMapper implements ExceptionMapper<EJBException> {

	@Override
	public Response toResponse(EJBException exception) {		
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
				.entity(exception.getMessage()).build();
	}
}
