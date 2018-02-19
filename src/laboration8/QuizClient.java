package laboration8;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.sound.midi.ControllerEventListener;
import javax.swing.plaf.synth.SynthSeparatorUI;



public class QuizClient {
	QuizController qc;
	private Socket socket;
	private DataInputStream dis;
	private DataOutputStream dos;
	
	public void connect(String ip, int port) throws IOException {
		qc.waitingForConnect();
		socket = new Socket(ip,port);
		if(socket.isConnected()){
			qc.connected();
		}
		dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
		dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
		
		new Listener().start();
	}
	
	public QuizClient(QuizController qc) {
		this.qc = qc;
	}
	
	public void setClientController(QuizController controller) {
		this.qc = controller;
	}
	
	public void question(int index) throws IOException{
		
		dos.writeUTF("QUESTION");
		dos.writeInt(index);
		dos.flush();
		
		String request = dis.readUTF();
		int nbr = dis.readInt();
		String question = dis.readUTF();
		
		qc.uiAppend(nbr +": "+question);
	}
	
	public void answer(int index, int answer){
		
	}
	
	
	private class Listener extends Thread {
		public void run() {
			try {
				String res = dis.readUTF();
				System.out.println(res);
				qc.uiAppend(res);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	}
}
