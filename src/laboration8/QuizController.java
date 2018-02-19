package laboration8;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class QuizController {
	private QuizUI ui = new QuizUI(this);
	private QuizClient client;
	
	private DataInputStream dis;
	private DataOutputStream dos;

	public QuizController() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.add(ui);
				frame.pack();
				frame.setVisible(true);
			}
		});
		client = new QuizClient(this);
	}

	public void connect() throws IOException{
		client.connect(ui.getIp(), ui.getPort());
		
	}

	public void disconnect(){
		
	}

	public void question() throws IOException{
		client.question(ui.getQuestionIndex());
	}
	
	public void answer(){
		client.answer();
	}
	
	public void uiAppend(final String txt){
		ui.setTextTest(txt);
	}
	
	public void connected(){
		ui.connected("Ansluten");
		ui.enableAnswer();
	}
	
	public void waitingForConnect(){
		ui.waitingForConnection("Väntar nååb");
	}

	


	public static void main(String[] args) {
		SwingUtilities.invokeLater( new Runnable() {
			public void run() {
				QuizController prog = new QuizController();
			}
		});
	}
}
