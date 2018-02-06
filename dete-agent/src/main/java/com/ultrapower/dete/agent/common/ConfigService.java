package com.ultrapower.dete.agent.common;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigService {
	
	private static Logger log = LoggerFactory.getLogger(ConfigService.class);
	private static Map<String, Object> params = new HashMap<String, Object>();
	
	private void ConfigService() {
		Properties prop = new Properties();
		try {
			InputStream in = getClass().getResourceAsStream("server.properties");
			prop.load(in);
			Set<Object> keyValue = prop.keySet();
			for (Iterator<Object> it = keyValue.iterator(); it.hasNext();) {
				String key = (String) it.next();
				params.put(key, prop.getProperty(key));
			}			
		} catch (Exception e) {
			log.info("读取配置文件出错" + e.getLocalizedMessage());
		}
	}
	
	private static class SingletonHolder {
		private static ConfigService instance = new ConfigService();
	}
	
	public static String getValue(String key) {
		String val = null;
		if (params != null && params.containsKey(key)) {
			Object value = params.get(key);
			val = value==null?null:String.valueOf(value);
		}
		return val;
	}

}
