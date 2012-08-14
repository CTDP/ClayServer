package net.ctdp.clay.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;

import net.ctdp.clay.ClayServer;

public class ClayView extends JFrame {

	public ClayView(final String title, final String url) {
		setTitle(title);
		setPreferredSize(new Dimension(820, 370));
		setLayout(new BorderLayout());
		setResizable(false);

		initComponents(url);

		pack();
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void initComponents(final String url) {
		JPanel intro = new JPanel();
		intro.setLayout(new BorderLayout());
		intro.setBackground(Color.WHITE);
		try {
			InputStream is = ClayServer.class.getResourceAsStream("/res/intro.jpg");
			ImageIcon imageIcon = new ImageIcon(ImageIO.read(is));
			intro.add(new JLabel(imageIcon), BorderLayout.LINE_START);
		} catch (IOException ex) {

		}

		JTextPane webView = new MoreWebbableView("res/guide.html");
		intro.add(webView, BorderLayout.CENTER);

		add(intro, BorderLayout.CENTER);


		JButton startViewerButton = new JButton("Open Viewer");
		startViewerButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					MoreWebbableView.openURL(new URI(url));
				} catch (URISyntaxException e1) {
					e1.printStackTrace();
				}
			}
		});
		JPanel panel = new JPanel();
		JTextField urlField = new JTextField(url);
		urlField.setEditable(false);
		panel.add(urlField);
		panel.add(startViewerButton);
		panel.add(new JLabel("<html><i>You need a Webbrowser supporting WebGL like Chrome or Firefox."));
		add(panel, BorderLayout.PAGE_END);
	}
	
}
