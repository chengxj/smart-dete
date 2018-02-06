package com.ultrapower.dete.server.common;

import org.glassfish.jersey.server.ResourceConfig;
import com.ultrapower.dete.server.api.ApiController;

public class ApiConfig extends ResourceConfig {
	
	public ApiConfig() {
        //加载Resource
        register(ApiController.class);
	}

}
