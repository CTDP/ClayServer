package net.ctdp.clay;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jibble.simplewebserver.SimpleWebServer;

public class ClayServer {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {

		File documentRoot = new File("./htdocs/");
		int port = 20042;
		boolean recursive = true;
		SimpleWebServer server = new SimpleWebServer(documentRoot, port);
		
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
		JFrame frame = new JFrame("Clay Server");
		frame.setPreferredSize(new Dimension(300, 200));

		JPanel panel = new JPanel();
		
		panel.add(new JLabel("<html><p>Welcome to the Clay carviewer. " +
				"<p><p>However, this is not the viewer. The viewer is an <br>" +
				"interactive website that previews the model in your browser.<br>" +
				"This tool starts the server to run this website." +
				"<p><p>Open the viewer by visiting the URL address below." +
				"<p><p>Note, you can use this url from any device within your network.<br>" +
				"Try preview on your mobile device."));
		panel.add(new JTextField(url));
		JButton startViewerButton = new JButton("Open Viewer");
		startViewerButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					openURL(new URI(url));
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panel.add(startViewerButton);
		panel.add(new JLabel("You need a Webbrowser supporting WebGL like Chrome or Firefox."));
		frame.add(panel);

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
