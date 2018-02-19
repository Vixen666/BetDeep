package laboration8;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JOptionPane;

public class DemoQuizClient1 {
	private String ip;
	private int port;
	
	public DemoQuizClient1(String ip, int port) {
		this.ip = ip;
		this.port = port;
		new Communicate().start();
	}
	
	private void question(String[] parts) {
		try(Socket socket = new Socket(ip,port);
				DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
				DataInputStream dis = new DataInputStream(socket.getInputStream()) ) {
			System.out.println(dis.readUTF()); // välkommen
			dos.writeUTF(parts[0]);
			dos.writeInt(Integer.parseInt(parts[1]));
			dos.flush();
			String request = dis.readUTF();
			int nbr = dis.readInt();
			String question = dis.readUTF(); 
			System.out.println(request+": "+nbr+"\n"+question);
		} catch(IOException e) {
			System.out.println(e.toString());
		} 
	}
	
	private void answer(String[] parts) {
		try(Socket socket = new Socket(ip,port);
				DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
				DataInputStream dis = new DataInputStream(socket.getInputStream()) ) {
			System.out.println(dis.readUTF()); // välkommen
			dos.writeUTF(parts[0]);
			dos.writeInt(Integer.parseInt(parts[1]));
			dos.writeInt(Integer.parseInt(parts[2]));
			dos.flush();
			String request = dis.readUTF();
			int nbr = dis.readInt();
			String question = dis.readUTF(); 
			System.out.println(request+": "+nbr+"\n"+question);
		} catch(IOException e) {
			System.out.println(e.toString());
		}
	}
	
	private class Communicate extends Thread {
		public void run() {
			String request="";
			while(true) {
				try {
					request = JOptionPane.showInputDialog("Ange request på formen:\n'QUESTION,8' eller \n'ANSWER,8,1932'");
					if(request==null)
						break;
					String[] parts = request.split(",");
					if(parts.length==2 && "QUESTION".equals(parts[0].toUpperCase())) {
						question(parts);
					} else if(parts.length==3 && "ANSWER".equals(parts[0].toUpperCase())) {
						answer(parts);
					}
				} catch(Exception e) {
					System.out.println("Bad arguments: " + request);
				}
			}
			System.out.println("\nDemoQuizClient avslutas");
		}
	}
	
	public static void main(String[] args) {
		new DemoQuizClient1("195.178.227.53",7721);
	}
}
