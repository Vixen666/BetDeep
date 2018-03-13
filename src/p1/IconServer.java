package p1;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Icon;

/**
 * Implementation of the IconServer
 * A Server for sending Icon-objects via Buffered object streams
 * @author Vixen666
 *
 */

public class IconServer implements Observer{
	private IconManager iconManager;
	private LinkedList<ClientHandler> clients = new LinkedList<>();
	private Icon currentIcon;
	private Icon LastIcon = null;

	/**
	 * Starts the inner class Connection 
	 * @param iconManager the Observable-iconmanger
	 * @param port the port the server listens on
	 */
	public IconServer(IconManager iconManager, int port) {
		this.iconManager = iconManager;
		iconManager.addObserver(this);
		new Connection(port).start();
	}

	/**
	 * Inner class Connections, Listens for incomming connections and starts a new Thread for every connection.
	 * @author Vixen666
	 *
	 */
	private class Connection extends Thread {
		private int port;

		/**
		 * Constructor, just sets the port
		 * @param port
		 */
		public Connection(int port) {
			this.port = port;
		}

		/**
		 * Listens for incomming connection and starts a new thread pf ClientHandler for each client
		 */
		public void run() {
			Socket socket = null;
			System.out.println("Server startad");
			try (ServerSocket serverSocket = new ServerSocket(port)) {
				System.out.println("Lyssnar pÃ¥ port nr " + serverSocket.getLocalPort());
				while(true) {
					try {
						socket = serverSocket.accept();
						clients.add(new ClientHandler(socket));
					} catch(IOException e) {
						System.err.println(e);
						if(socket!=null)
							socket.close();
					}
				}
			} catch(IOException e) {
				System.err.println(e);
			}
			System.out.println("Server stoppad");
		}
	}

	/**
	 * Inner class Clienthandler. Handles one client each
	 * @author Vixen666
	 *
	 */
	private class ClientHandler extends Thread {
		private ObjectOutputStream oos;

		/**
		 * Configures a stream on the socket ad starts the run method
		 * @param socket Socket from Connection-class
		 * @throws IOException
		 */
		public ClientHandler(Socket socket) throws IOException {
			oos = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
			start();
		}
		
		/**
		 * Writes the object to the socket
		 * @param icon
		 * @throws IOException
		 */

		public void send(Icon icon) throws IOException {
			oos.writeObject(icon);
			run();
		}
		
		/**
		 * Flushes the socket, sending the current icon
		 */

		public void run() {
			try {		
				oos.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Updatemethod from Observer. Recievs a Icon, loops through all current connections and sends them the icon
	 */
	@Override
	public void update(Observable o, Object arg) {
		for(ClientHandler client : clients) {
			try {
				client.send((Icon)arg);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
