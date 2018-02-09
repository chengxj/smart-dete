package com.ultrapower.dete.server.common;

import org.apache.camel.spi.ExceptionHandler;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;

public class ApiConfig extends ResourceConfig {
	
	public ApiConfig() {
        // 配置api发布目录
		packages("com.ultrapower.dete.server.api");
//		packages("simm.spring.restapi.resource");
		// 
		// 注册日志
        register(LoggingFeature.class);
        // 异常处理
//        register(ExceptionHandler.class);
        // 跨域过滤器注册
//        register(CorsFilter.class);
	}
	
//	public class ExceptionHandler 

}
