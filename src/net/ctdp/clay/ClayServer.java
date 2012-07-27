package net.ctdp.clay;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
		
		startGUI(url);

		// TODO add gui
		/*Path dir = Paths.get(documentRoot.toURI());
        WatchDir wd = new WatchDir(dir, recursive);
        wd.processEvents();*/


	}

	private static void startGUI(final String url) {
		JFrame frame = new JFrame("Clay Server for CTDP IFM 2009");
		frame.setPreferredSize(new Dimension(820, 320));
		frame.setLayout(new BorderLayout());
		
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
				"<p><p>However, this is not the viewer. The viewer is an <br>" +
				"interactive website that previews the model in your browser.<br>" +
				"This tool starts the server to run this website." +
				"<p><p><b>Open the viewer by visiting the URL address below.</b>" +
				"<p><p>Note, you can use this url from any device within your network.<br>" +
				"Try preview on your mobile device." +
				"<p><p>For preview of your textures, export your texture from <br>" +
				"the template into <i>carbody.jpg</i> and <i>carbodyExtra0.jpg</i>";
		JTextField descriptionField = new JTextField(description);
		descriptionField.setEnabled(false);
		descriptionField.setOpaque(false);
		intro.add(new JLabel(description), BorderLayout.LINE_END);
		frame.add(intro, BorderLayout.CENTER);
		
		
		JButton startViewerButton = new JButton("Open Viewer");
		startViewerButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					openURL(new URI(url));
				} catch (URISyntaxException e1) {
					e1.printStackTrace();
				}
			}
		});
		JPanel panel = new JPanel();
		JTextField urlField = new JTextField(url);
		panel.add(urlField);
		panel.add(startViewerButton);
		panel.add(new JLabel("<html><i>You need a Webbrowser supporting WebGL like Chrome or Firefox."));
		frame.add(panel, BorderLayout.PAGE_END);

		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}


	private static void openURL(URI uri) {
		if( !java.awt.Desktop.isDesktopSupported() ) {

			System.err.println( "Desktop is not supported (fatal)" );
			System.exit( 1 );
		}

		java.awt.Desktop desktop = java.awt.Desktop.getDesktop();

		if( !desktop.isSupported( java.awt.Desktop.Action.BROWSE ) ) {

			System.err.println( "Desktop doesn't support the browse action (fatal)" );
			System.exit( 1 );
		}

		try {

			desktop.browse( uri );
		}
		catch ( Exception e ) {

			System.err.println( e.getMessage() );
		}
	}

}
