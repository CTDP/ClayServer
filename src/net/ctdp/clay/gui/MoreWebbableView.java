package net.ctdp.clay.gui;

import java.awt.Font;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.html.HTMLDocument;

import net.ctdp.clay.ClayServer;

public class MoreWebbableView extends JTextPane {

	public MoreWebbableView(String path) {

		// add a CSS rule to force body tags to use the default label font
		// instead of the value in javax.swing.text.html.default.csss
		Font font = UIManager.getFont("Label.font");
		String bodyRule = "body { font-family: " + font.getFamily() + "; " +
				"font-size: " + font.getSize() + "pt; }";

		try {
			File file = new File(path);
			URL descriptionUrl;
			if(file.exists())
				descriptionUrl = file.toURL();
			else 
				descriptionUrl = getClass().getResource("/"+path);
			setPage(descriptionUrl);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}

		((HTMLDocument)getDocument()).getStyleSheet().addRule(bodyRule);
		setOpaque(false);
		setEditable(false);
		addHyperlinkListener(new HTMLListener());
	}

	private class HTMLListener implements HyperlinkListener {
		public void hyperlinkUpdate(HyperlinkEvent e) {
			if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
				try {
					ClayServer.openURL(e.getURL().toURI());
				} catch (URISyntaxException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		}
	}

}
