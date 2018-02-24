package com.ultrapower.dete.server.common;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class ExceptionHandler implements ExceptionMapper<Exception> {

	public Response toResponse(Exception e) {
		if (e instanceof NotFoundException) {
			return Response.ok().entity(Response.Status.OK).build();			
		} else if (e instanceof WebApplicationException) {
			return Response.ok().entity(Response.Status.OK).build();
//			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
		return Response.ok().entity(Response.Status.OK).build();
	}

}
