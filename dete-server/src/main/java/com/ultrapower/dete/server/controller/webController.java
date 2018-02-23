package com.ultrapower.dete.server.controller;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.mvc.Viewable;

@Path("/web")
public class webController {
	
	@Path("/test")
	@Produces(MediaType.TEXT_HTML)
	public Response getTest() {
		return Response.ok(new Viewable("/index")).build();
	}

}
