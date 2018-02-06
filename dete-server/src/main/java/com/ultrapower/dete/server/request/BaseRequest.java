package com.ultrapower.dete.server.request;

import java.io.Serializable;

public class BaseRequest implements Serializable {
	
	private static final long serialVersionUID = 1L;
	public String id;
	public String name;
	public String pwd;
	public String type = "";

}
