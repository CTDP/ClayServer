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
import net.ctdp.clay.io.Configuration;

/**
 * View UI
 * @author danielsenff
 *
 */
public class ClayView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Configuration configuration;
	
	/**
	 * @param configuration
	 * @param url
	 */
	public ClayView(final Configuration configuration, final String url) {
		setTitle(configuration.title);
		setPreferredSize(new Dimension(820, 370));
		setLayout(new BorderLayout());
		setResizable(false);
		this.configuration = configuration;

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
			InputStream is = ClayServer.class.getResourceAsStream(configuration.introGraphic);
			ImageIcon imageIcon = new ImageIcon(ImageIO.read(is));
			intro.add(new JLabel(imageIcon), BorderLayout.LINE_START);
		} catch (IOException ex) {

		}

		JTextPane webView = new MoreWebbableView(configuration.descriptionPage);
		intro.add(webView, BorderLayout.CENTER);

		add(intro, BorderLayout.CENTER);

		JButton startViewerButton = new JButton(configuration.openButtonText);
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
