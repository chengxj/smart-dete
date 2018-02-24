package com.ultrapower.dete.server.common.exception;

/**
 * 静态资源请求异常
 * @author chengxj
 *
 */
public class WebException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public WebException(String message) {
		super(message);
	}
	
	public WebException(String message, Throwable cause) {
		super(message, cause);		
	}
	
	public WebException(Throwable cause) {
		super(cause);
	}

}
