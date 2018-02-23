package com.ultrapower.dete.server.common;

import org.glassfish.jersey.server.ResourceConfig;

public class ApiConfig extends ResourceConfig {
	
	public ApiConfig() {        
		packages("com.ultrapower.dete.server.controller");// 配置api发布目录
//        register(JspMvcFeature.class);//注册MVC支持
//        property(JspMvcFeature.TEMPLATE_BASE_PATH, "/WEB-INF");//jsp文件所在位置
//        register(LoggingFeature.class);// 注册日志
	}

}
