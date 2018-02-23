package com.ultrapower.dete.server.controller;

import javax.ws.rs.Path;
import org.glassfish.jersey.server.mvc.Viewable;

@Path("/web/")
public class WebController {
	
	@Path("/index")
	public Viewable getTest() {
		return new Viewable("/index");
	}

}
