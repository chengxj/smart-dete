package com.ultrapower.dete.agent;

import javax.jms.JMSException;

import com.ultrapower.dete.agent.common.ConfigService;

/**
 * dete-agent start service
 * @author chengxj
 * @since 2018年02月06
 */
public class SmartDeteAgent {
	
    public static void main( String[] args ) throws JMSException {    	
    	System.out.println("==" + ConfigService.getInstance().getValue("host"));
//        String user = "admin";
//        String password = "admin";
//        String destination = "event";
//        int messages = 10000;
//        int size = 256;
//
//        String DATA = "abcdefghijklmnopqrstuvwxyz";
//        String body = "";
//        for( int i=0; i < size; i ++) {
//            body += DATA.charAt(i%DATA.length());
//        }
//        
//        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");
//
//        Connection connection = factory.createConnection(user, password);
//        connection.start();
//        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//        Destination dest = new ActiveMQTopic(destination);
//        MessageProducer producer = session.createProducer(dest);
//        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
//        for( int i=1; i <= messages; i ++) {
//            TextMessage msg = session.createTextMessage(body);
//            msg.setIntProperty("id", i);
//            producer.send(msg);
//            if( (i % 1000) == 0) {
//                System.out.println(String.format("Sent %d messages", i));
//            }
//        }
//        producer.send(session.createTextMessage("SHUTDOWN"));
//        connection.close();
    }
    
}