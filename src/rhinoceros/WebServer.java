package rhinoceros;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.nio.SelectChannelConnector;

public class WebServer
{
	public static int port = 7896;

	public void Listen() throws Exception {
		Server server = new Server();
		SelectChannelConnector connector = new SelectChannelConnector();
		connector.setPort(port);
		server.addConnector(connector);

		WebServerHandler handler = new WebServerHandler();
		server.setHandler(handler);

		server.start();
		server.join();
	}
}