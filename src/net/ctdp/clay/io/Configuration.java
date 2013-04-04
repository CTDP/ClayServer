package net.ctdp.clay.io;

import java.util.HashMap;

public class Configuration {

	public int port = 20021;
	public String title = "ClayServer - Bundled stand-alone HTTP-Server ";
	public String introGraphic = "/res/intro.jpg";
	public String descriptionPage = "res/guide.html";
	public String openButtonText = "Open Browser";
	public HashMap<String, String> urlHandlers = new HashMap<String, String>();
	
}
