package net.ctdp.clay;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.URI;

import net.ctdp.clay.gui.ClayView;
import net.ctdp.clay.io.ConfigParser;
import net.ctdp.clay.io.Configuration;

import org.jibble.simplewebserver.SimpleWebServer;

public class ClayServer {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {

		// read config
		InputStream configIS = ClayServer.class.getResourceAsStream("/res/config.ini");
		ConfigParser configParser = new ConfigParser();
		configParser.parse(configIS);
		Configuration configuration = configParser.getConfig(); 
		
		File documentRoot = new File("./htdocs/");
		int port = configuration.port;
		SimpleWebServer server = new SimpleWebServer(documentRoot, port, configuration.urlHandlers);
		
		InetAddress thisIp =InetAddress.getLocalHost();
		String url = "http://"+thisIp.getHostAddress()+":"+port+"/";
		System.out.println("IP:"+thisIp.getHostAddress());
		
		new ClayView("Clay Server for CTDP IFM 2009", url);

		// TODO add gui
		/*Path dir = Paths.get(documentRoot.toURI());
        WatchDir wd = new WatchDir(dir, recursive);
        wd.processEvents();*/

	}

}
