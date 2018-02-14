package laboration7;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;



public class ReadServerFile {
	public ReadServerFile() {
		JFrame frame = new JFrame("IP");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new UI());
		frame.pack();
		frame.setVisible(true);
	}

	private class UI extends JPanel implements ActionListener {
		private JLabel lblLocalIp = new JLabel(" ");
		private JTextField tfHostname = new JTextField();
		private JLabel lblIp = new JLabel(" ");

		public UI() {
			JPanel pnlHostname = new JPanel(new BorderLayout());
			pnlHostname.add(new JLabel("Host name:"),BorderLayout.WEST);
			pnlHostname.add(tfHostname);
			//lblIp.setSize(new Dimension(200, 1500));
			lblLocalIp.setFont(new Font("SansSerif",Font.PLAIN,10));
			try {
				InetAddress localAddress = InetAddress.getLocalHost();
				lblLocalIp.setText("IP="+localAddress.getHostAddress()+",Namn="+localAddress.getHostName());
			} catch (UnknownHostException e) {
				lblLocalIp.setText("IP=-, Namn=-");
			}

			setLayout(new GridLayout(3,1));
			setPreferredSize(new Dimension(300,100));
			add(lblLocalIp);
			add(pnlHostname);
			add(lblIp);

			tfHostname.addActionListener(this);
		}

		public void actionPerformed(ActionEvent e) {
			try {
				URL u = new URL(tfHostname.getText());
				URLConnection conn = u.openConnection();
				
				System.out.println(conn.getContentType());
				if(conn.getContentType().equals("text/xml")){
					try(InputStreamReader isr = new InputStreamReader(u.openStream(),"UTF-8");
							BufferedReader br =new BufferedReader(isr)){
					String line = br.readLine();
					String output = "";
					while(line !=null){
						output += "\n\r" +line;
						lblIp.setText(output);
						line = br.readLine();
					}
					JOptionPane.showMessageDialog(null,output);
				
					}
				
				}else if(conn.getContentType().equals("image/png")){
					ImageIcon icon = new ImageIcon(u);
					JOptionPane.showMessageDialog(null,icon);
				}
				
				
					
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new ReadServerFile();
			}
		});
	}
}
