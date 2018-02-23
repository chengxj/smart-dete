package com.ultrapower.dete.server.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ultrapower.dete.server.dto.BaseDTO;
import com.ultrapower.dete.server.request.BaseRequest;

@Path("/api")
public class ApiController {
	
	@GET
	@Path("/test")
	@Produces(MediaType.APPLICATION_JSON)
	public BaseDTO<BaseRequest> getTest() {
		BaseDTO<BaseRequest> dto = new BaseDTO<BaseRequest>();
		dto.status = 200;
		BaseRequest obj = new BaseRequest();		
		obj.id = "test";
		obj.name = "test";
		obj.pwd = "test";
		dto.setData(obj);
		dto.ready();
		return dto;
	}
	
	@POST
	@Path("/test")
	@Produces(MediaType.APPLICATION_JSON)
	public BaseDTO<BaseRequest> getTest(BaseRequest request) {
		BaseDTO<BaseRequest> dto = new BaseDTO<BaseRequest>();
		if (request != null) {
			dto.status = 200;
			List<BaseRequest> data = new ArrayList<BaseRequest>();
			data.add(request);
			dto.setData(data);
		} else {
			dto.status = 503;
		}
		dto.ready();
		return dto;
	}

}
