package com.ultrapower.dete.server.common;

import java.io.IOException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;

public class AuthorizationRequestFilter implements ContainerRequestFilter {
	
	public void filter(ContainerRequestContext requestContext) throws IOException {
		String token = requestContext.getHeaderString("token");
	}

}
