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



public class IconServer implements Observer{
	private IconManager iconManager;
	private LinkedList<ClientHandler> clients = new LinkedList<>();
	private Icon currentIcon;
	private Icon LastIcon = null;

	public IconServer(IconManager iconManager, int port) {
		this.iconManager = iconManager;
		iconManager.addObserver(this);
		new Connection(port).start();
	}

	private class Connection extends Thread {
		private int port;

		public Connection(int port) {
			this.port = port;
		}

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

	private class ClientHandler extends Thread {
		private ObjectOutputStream oos;

		public ClientHandler(Socket socket) throws IOException {
			oos = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
			System.out.println(oos);
			start();
		}

		public void send(Icon icon) throws IOException {
			oos.writeObject(icon);
			run();
		}

		public void run() {
			try {		
				oos.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

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
