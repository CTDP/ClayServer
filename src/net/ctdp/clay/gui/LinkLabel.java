package net.ctdp.clay.gui;

import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;

import javax.accessibility.AccessibleContext;
import javax.accessibility.AccessibleText;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class LinkLabel extends JLabel {

	public LinkLabel(String text, Icon icon, int horizontalAlignment) {
		super("<html>" + text + "</html>", icon, horizontalAlignment);
		additionalInit();
	}

	protected void additionalInit() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String link = getLink(e.getPoint());
				if (link == null)
					return;
				try {
					Desktop.getDesktop().browse(new URI(link));
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(
							null,
							"<html><p>Link could not be opened. <p>"+ex.getMessage()+"</html>",
							"Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		addMouseMotionListener(new MouseAdapter() {
			boolean pointedOnALink = false;
			@Override
			public void mouseMoved(MouseEvent e) {
				boolean pointsOnALink = getLink(e.getPoint()) != null;
				if (pointsOnALink != pointedOnALink) {
					if (pointsOnALink) {
						setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
					} else {
						setCursor(Cursor.getDefaultCursor());
					}
					pointedOnALink = pointsOnALink;
				}
			}
		});
	}

	/*
	 * Ermittelt den Index des Zeichens, das sich am Punkt p befindet.
	 * Gibt ggf. einen Link zurueck.
	 */
	protected String getLink(Point p) {
		AccessibleContext aC = getAccessibleContext();
		if (aC instanceof AccessibleJLabel) {
			AccessibleJLabel aL = (AccessibleJLabel) aC;
			AccessibleText aT = aL.getAccessibleText();
			if (aT == null) {
				return null;
			}
			int index = aL.getIndexAtPoint(p);
			// System.out.println(index);
			if (index >= 37 && index <= 120) {
				return "http://www.java-forum.org/codeschnipsel-u-projekte/107710-klickbare-link-jlabel.html";
			}
			if (index >= 134 && index <= 149) {
				return "mailto:mail.mir@hier.de";
			}
		}
		return null;
	}
	/*public LinkLabel(String url) {
		this(url, url);
	}

	public LinkLabel(String label, String url) {
		super(label);
		setForeground(Color.BLUE);
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		addMouseListener(new URLMouseListener(url));
	}*/

}
