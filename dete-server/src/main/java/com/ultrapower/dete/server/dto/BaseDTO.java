package com.ultrapower.dete.server.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BaseDTO<T> implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public int status;
	private List<T> data;
	public String message;
	public long beginTime;
	public long time;
	
	public BaseDTO(){
		this.beginTime = System.currentTimeMillis();
	}
	
	public void ready() {
		this.time = System.currentTimeMillis() - this.beginTime;
	}
	
	public void setData(T data) {
		List<T> dto = new ArrayList<T>();
		dto.add(data);
		this.data = dto;
	}
	
	public void setData(List<T> data) {
		this.data = data;		
	}
	
	public List<T> getData() {
		return this.data;
	}
	
}
