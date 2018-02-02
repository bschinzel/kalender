package de.schinzel.abwesenheit.rest.exceptionmapper;

import javax.ejb.EJBTransactionRolledbackException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class EJBTransactionRolledbackExceptionMapper implements ExceptionMapper<EJBTransactionRolledbackException> {

	@Override
	public Response toResponse(EJBTransactionRolledbackException exception) {
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
				.entity(exception.getMessage()).build();
	}
}
