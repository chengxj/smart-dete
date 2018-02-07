package com.ultrapower.dete.server.common;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
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
	private String PATH = this.getClass().getClassLoader().getResource("server.properties").getPath();
	
	private ConfigService() {
		Properties prop = new Properties();
		InputStream in = null;
		try {
			in = new BufferedInputStream(new FileInputStream(PATH));
			prop.load(in);
			in.close();
			Set<Object> keyValue = prop.keySet();
			for (Iterator<Object> it = keyValue.iterator(); it.hasNext();) {
				String key = (String) it.next();
				params.put(key, prop.getProperty(key));
			}			
		} catch (Exception e) {
			log.info("读取配置文件出错" + e.getLocalizedMessage());
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					log.info("读取配置文件出错" + e.getLocalizedMessage());
				}
				in = null;
			}
			if (prop != null) {
				prop = null;
			}
		}
	}
	
	private static class SingletonHolder {
		private static ConfigService instance = new ConfigService();
	}
	
	public static ConfigService getInstance(){
		return SingletonHolder.instance;		
	}
	
	public String getValue(String key) {
		String val = null;
		if (params != null && params.containsKey(key)) {
			Object value = params.get(key);
			val = value==null?null:String.valueOf(value);
		}
		return val;
	}
	
	public int getIntValue(String key) {
		int dto = 0;
		String val = null;
		if (params != null && params.containsKey(key)) {
			Object value = params.get(key);
			val = value==null?null:String.valueOf(value);
		}
		if (val!= null) {
			dto = Integer.parseInt(val);
		}
		return dto;
	}

}
