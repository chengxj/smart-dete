package com.ultrapower.dete.agent;

import javax.jms.JMSException;
import com.ultrapower.dete.agent.service.mq.MqListener;

/**
 * dete-agent start service
 * @author chengxj
 * @since 2018年02月06
 */
public class SmartDeteAgent {
	
    public static void main( String[] args ) throws JMSException {
    	MqListener.getInstance();
    }
    
}