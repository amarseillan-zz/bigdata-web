package bigdata.web;



import org.apache.log4j.Logger;
import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.bio.SocketConnector;
import org.mortbay.jetty.webapp.WebAppContext;

public class RunBigdata {
	static private final Logger LOGGER = Logger.getLogger(bigdata.web.RunBigdata.class);
	public static void main(String[] args) throws Exception {
	
		Server server = new Server();
		SocketConnector connector = new SocketConnector();
		connector.setMaxIdleTime(1000 * 60 * 60);
		connector.setSoLingerTime(-1);
		connector.setPort(8081);
		server.setConnectors(new Connector[] { connector });
		WebAppContext bb = new WebAppContext();
		bb.setServer(server);
		bb.setContextPath("/bigdata");
		bb.setWar("src/main/webapp");
		server.addHandler(bb);
		try {
			LOGGER.info(">>> STARTING EMBEDDED JETTY SERVER, "
					+ "PRESS ANY KEY TO STOP");
			server.start();
			while (System.in.available() == 0) {
				Thread.sleep(5000);
			}
			server.stop();
			server.join();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(100);
		}
	}
}