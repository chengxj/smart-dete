package com.ultrapower.dete.server;

import java.io.File;
import java.net.URI;
import javax.ws.rs.core.UriBuilder;
import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.broker.TransportConnector;
import org.apache.activemq.store.kahadb.KahaDBStore;
import org.glassfish.jersey.jetty.JettyHttpContainerFactory;
import com.ultrapower.dete.server.common.ApiConfig;
import com.ultrapower.dete.server.common.ConfigService;

/**
 * dete-server start service
 * @author chengxj
 * @since 2018年2月6号
 */
public class SmartDeteServer {
	
	public static final String HOST = "http://" + ConfigService.getInstance().getValue("host") + "/";
	public static final int PORT = ConfigService.getInstance().getIntValue("port");
	public static final String KAHA_PATH = ConfigService.getInstance().getValue("kaha.path");
	public static final String CONNECTOR_NAME = ConfigService.getInstance().getValue("connector.name");
	public static final String CONNECTOR_URI = ConfigService.getInstance().getValue("connector.uri");

    public static void main( String[] args ) throws Exception {
    	// 启动mq
    	BrokerService broker = new BrokerService();
    	broker.setBrokerName("dete-server");
    	broker.setDataDirectory("data");
    	broker.setUseShutdownHook(false);
    	KahaDBStore kahaAdapter = new KahaDBStore();
    	kahaAdapter.setDirectory(new File(KAHA_PATH));
    	broker.setPersistenceAdapter(kahaAdapter);
    	TransportConnector connector = new TransportConnector();
    	connector.setName(CONNECTOR_NAME);
    	connector.setUri(new URI(CONNECTOR_URI));
    	broker.addConnector(connector);
    	broker.start();
    	// 启动api
    	URI baseUri = UriBuilder.fromUri(HOST).port(PORT).build();
    	ApiConfig config = new ApiConfig();
    	JettyHttpContainerFactory.createServer(baseUri, config);
    }
    
}