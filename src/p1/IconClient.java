package p1;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Icon;

/**
 * IconClient, recievs icons from a IconServe
 * @author Vixen666
 *
 */
public class IconClient{
	private Socket socket;
	private ObjectInputStream ois;
	private ArrayList<IconClientCallback> listeners = new ArrayList<>();
	
	/**
	 * Opens a socket and creates a ObjectStream.
	 * Starts a Listener
	 * @param ip ip of the serve
	 * @param port port of the server
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public IconClient(String ip, int port) throws UnknownHostException, IOException {
		socket = new Socket(ip, port);
		ois = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
		new Listener().start();
	}
	
	/**
	 * Adds a callback
	 * @param listener
	 */
	public void addProgressListener(IconClientCallback listener){ 
		this.listeners.add(listener); 
	}
	
	/**
	 * Innerclass, recievs a Icon from the server, and sends it to connected Viewers
	 * @author Vixen666
	 *
	 */
	private class Listener extends Thread{
		
		public void run() {
			Icon response;
			while(true) {
				try {
					response = (Icon) ois.readObject();
					for(IconClientCallback listener : listeners) {
						listener.notifyListeners(response);
					}
				} catch (ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}		
		}		
	}
}
