package net.ctdp.clay.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import net.ctdp.clay.ClayServer;

public class ClayView extends JFrame {

	public ClayView(final String title, final String url) {
		setTitle(title);
		setPreferredSize(new Dimension(820, 320));
		setLayout(new BorderLayout());
		
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
		String description = "<html><p><b>Welcome to the Clay car viewer for CTDP IFM 2009.</b>" +
				"<p><p>However</a>, this is not the viewer. The viewer is an <br>" +
				"interactive website that previews the model in your browser.<br>" +
				"This tool starts the server to run this website." +
				"<p><p><b>Open the viewer by visiting the URL address below.</b>" +
				"<p><p>Note, you can use this url from any device within your network.<br>" +
				"Try preview on your mobile device." +
				"<p><p>For preview of your textures, export your texture from <br>" +
				"the template into <i>carbody.jpg</i> and <i>carbodyExtra0.jpg</i>";
		JTextPane descriptionField = new JTextPane();
		descriptionField.setText(description);
		descriptionField.setEnabled(false);
		descriptionField.setOpaque(false);
		intro.add(new JLabel(description), BorderLayout.LINE_END);
		
		
		JPanel links = new JPanel();
		links.setBackground(Color.WHITE);
		JLabel linkWebsite = new LinkLabel("<a hrf=\"http://www.ctdp.net/ifm2009.html\">Website</a>", null, JLabel.CENTER);
		links.add(linkWebsite);
		/*JLabel linkSource = new LinkLabel("Source Code", "http://www.github.com/CTDP/ClayServer");
		links.add(linkSource);
		JLabel linkTracker = new LinkLabel("Bug Tracker", "http://www.github.com/CTDP/ClayServer/issues");
		links.add(linkTracker);*/
		intro.add(links, BorderLayout.PAGE_END);
		
		
		add(intro, BorderLayout.CENTER);
		
		
		JButton startViewerButton = new JButton("Open Viewer");
		startViewerButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					ClayServer.openURL(new URI(url));
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
