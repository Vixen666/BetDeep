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

public class IconClient{
	private Socket socket;
	private ObjectInputStream ois;
	private ArrayList<IconClientCallback> listeners = new ArrayList<>();
	
	public IconClient(String ip, int port) throws UnknownHostException, IOException {
		socket = new Socket(ip, port);
		ois = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
		new Listener().start();
	}
	
	public void addProgressListener(IconClientCallback listener){ 
		this.listeners.add(listener); 
	}
	
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
