package com.ultrapower.dete.agent.service.mq;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ultrapower.dete.agent.common.ConfigService;

/**
 * 消息发布
 * 不同的AGENT使用不同的IP作为队列名
 * @author chengxj
 *
 */
public class MqPublisher {
	
	private static Logger log = LoggerFactory.getLogger(MqPublisher.class);
	private static final String USER = "admin";
	private static final String PWD = "password";
	private static final String HOST = ConfigService.getInstance().getValue("server.host");
	private static final int PORT = ConfigService.getInstance().getIntValue("server.port");
	private static final String TOPIC = "COMMON_QUEUE";
	private static ActiveMQConnectionFactory factory = null;
	
	private MqPublisher() {
		factory = new ActiveMQConnectionFactory("tcp://" + HOST + ":" + PORT);		
	}
	
	private static class SingletonHolder {
		private static MqPublisher instance = new MqPublisher();
	}
	
	public static MqPublisher getInstance(){
		return SingletonHolder.instance;		
	}
	
	/**
	 * 向固定服务器,发送单条消息
	 * @param messages 消息列表
	 * @param server 服务器IP
	 */
	public void sendMessage(String message) {
		Connection connection = null;
		try {
			connection = factory.createConnection(USER, PWD);		
	        connection.start();
	        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	        Destination dest = new ActiveMQTopic(TOPIC);        
	        MessageProducer producer = session.createProducer(dest);
	        producer.setDeliveryMode(DeliveryMode.PERSISTENT);
	        producer.send(session.createTextMessage(message));
		} catch (JMSException e) {
			log.error("server发送消息出错.");
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (JMSException e) {
					log.error("server发送消息服务,关闭connection时出错.");
				}
				connection = null;
			}
		}
	}
	
	/**
	 * 向固定服务器,批量发送消息
	 * @param messages 消息列表
	 * @param server 服务器IP
	 */
	public void sendMessages(String[] messages) {
		Connection connection = null;
		try {
			connection = factory.createConnection(USER, PWD);		
	        connection.start();
	        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	        Destination dest = new ActiveMQTopic(TOPIC);        
	        MessageProducer producer = session.createProducer(dest);
	        producer.setDeliveryMode(DeliveryMode.PERSISTENT);
	        if (messages !=null && messages.length > 0) {
	        	for (int i = 0; i < messages.length; i++) {
	    	        producer.send(session.createTextMessage(messages[i]));	        		
	        	}
	        }
		} catch (JMSException e) {
			log.error("server发送消息出错.");
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (JMSException e) {
					log.error("server发送消息服务,关闭connection时出错.");
				}
				connection = null;
			}
		}
	}

}
