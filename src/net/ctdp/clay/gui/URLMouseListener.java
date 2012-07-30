package net.ctdp.clay.gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import java.net.URISyntaxException;

import net.ctdp.clay.ClayServer;

public class URLMouseListener extends MouseAdapter {

	private String url;
	
	public URLMouseListener(String url) {
		this.url = url;
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		try {
			ClayServer.openURL(new URI(url));
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}
	}
	
}
