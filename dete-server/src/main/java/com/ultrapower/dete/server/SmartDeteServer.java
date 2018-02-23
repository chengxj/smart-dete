package com.ultrapower.dete.server;

import java.io.File;
import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.broker.TransportConnector;
import org.apache.activemq.store.kahadb.KahaDBStore;
import org.apache.log4j.PropertyConfigurator;
import org.glassfish.jersey.jetty.JettyHttpContainerFactory;

import com.ultrapower.dete.server.common.ApiConfig;
import com.ultrapower.dete.server.common.ConfigService;
import com.ultrapower.dete.server.service.mq.MqListener;

/**
 * dete-server start service
 * @author chengxj
 * @since 2018年2月6号
 */
public class SmartDeteServer {
	
	public static final String HOST = "http://" + ConfigService.getInstance().getValue("host") + "/";
	public static final int PORT = ConfigService.getInstance().getIntValue("port");
	public static final String CONNECTOR_PATH = "data/" + ConfigService.getInstance().getValue("connector.path");
	public static final String CONNECTOR_NAME = ConfigService.getInstance().getValue("connector.name");

    public static void main( String[] args ) throws Exception {    	
		PropertyConfigurator.configure(SmartDeteServer.class.getResource("/log4j.properties").getFile());
    	// 启动ActiveMQ
    	BrokerService broker = new BrokerService();
    	broker.setBrokerName("dete-broker");
    	broker.setDataDirectory("data");
    	broker.setUseShutdownHook(false);
    	KahaDBStore kahaAdapter = new KahaDBStore();
    	kahaAdapter.setDirectory(new File(CONNECTOR_PATH));
    	broker.setPersistenceAdapter(kahaAdapter);
    	TransportConnector connector = new TransportConnector();
    	connector.setName(CONNECTOR_NAME==null?"openwire":CONNECTOR_NAME);
    	connector.setUri(new URI(getConnectorURI()));
    	broker.addConnector(connector);
    	broker.start();
    	MqListener.getInstance();// 订阅消息
    	// jetty启动api,发布http服务
    	URI baseUri = UriBuilder.fromUri(HOST).port(PORT).build();
    	ApiConfig config = new ApiConfig();
    	JettyHttpContainerFactory.createServer(baseUri, config);
    }
	
	private static String getConnectorURI() {
	    int port = ConfigService.getInstance().getIntValue("connector.port");
		int maximum = ConfigService.getInstance().getIntValue("connector.maximum");
		return "tcp://0.0.0.0:" + port + "?maximumConnections=" + maximum;
	}
    
}