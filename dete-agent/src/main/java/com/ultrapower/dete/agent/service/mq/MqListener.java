package com.ultrapower.dete.agent.service.mq;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ultrapower.dete.agent.common.ConfigService;

/**
 * 消息订阅
 * @author chengxj
 *
 */
public class MqListener {

	private static Logger log = LoggerFactory.getLogger(MqListener.class);
	private static final String USER = "admin";
	private static final String PWD = "password";
	private static final String HOST = ConfigService.getInstance().getValue("server.host");
	private static final int PORT = ConfigService.getInstance().getIntValue("server.port");
	private static final String TOPIC = ConfigService.getInstance().getValue("agent.host");
	
	private MqListener() {
		ActiveMQConnectionFactory factory = null;		
		Connection connection = null;
		try {
			factory = new ActiveMQConnectionFactory("tcp://" + HOST + ":" + PORT);		
			connection = factory.createConnection(USER, PWD);
	        connection.start();
	        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	        Destination dest = new ActiveMQTopic(TOPIC);
	        MessageConsumer consumer = session.createConsumer(dest);
	        consumer.setMessageListener(new consumerMessage());
		} catch (JMSException e) {
			log.error("消费消息出错.");			
		}
	}
	
	private static class SingletonHolder {
		private static MqListener instance = new MqListener();
	}
	
	public static MqListener getInstance(){
		return SingletonHolder.instance;		
	}
	
	private class consumerMessage implements MessageListener {

		public void onMessage(Message msg) {
			TextMessage textMsg = (TextMessage) msg;
			try {
				log.info(textMsg.getText());
			} catch (JMSException e) {
				log.error("消费消息出错.");
			}
		}

	}
	
}
