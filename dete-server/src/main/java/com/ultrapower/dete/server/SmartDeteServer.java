package com.ultrapower.dete.server;

import java.net.URI;
import javax.ws.rs.core.UriBuilder;
import org.apache.activemq.broker.BrokerFactory;
import org.apache.activemq.broker.BrokerService;
import org.glassfish.jersey.jetty.JettyHttpContainerFactory;
import com.ultrapower.dete.server.common.ApiConfig;

/**
 * dete-server start service
 * @author chengxj
 * @since 2018年2月6号
 */
public class SmartDeteServer {
	
	public static final String HOST = "http://10.0.0.248/";
	public static final int PORT = 8080;
	
    public static void main( String[] args ) throws Exception {
    	// 启动mq
    	BrokerService broker = BrokerFactory.createBroker(new URI("xbean:src/main/resources/mq.xml"));
    	broker.start();
    	// 启动api
    	URI baseUri = UriBuilder.fromUri(HOST).port(PORT).build();
    	ApiConfig config = new ApiConfig();
    	JettyHttpContainerFactory.createServer(baseUri, config);
    }
    
}