package com.ultrapower.dete.server.common.exception;

/**
 * 接口数据异常
 * @author chengxj
 *
 */
public class ApiException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ApiException(String message) {
		super(message);
	}
	
	public ApiException(String message, Throwable cause) {
		super(message, cause);		
	}
	
	public ApiException(Throwable cause) {
		super(cause);
	}

}
