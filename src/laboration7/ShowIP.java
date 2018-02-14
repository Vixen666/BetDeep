package laboration7;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class ShowIP {
	public ShowIP() {
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
				InetAddress address = InetAddress.getByName(tfHostname.getText());
				lblIp.setText("Host ip: " + address.getHostAddress());
			} catch (UnknownHostException e1) {
				e1.printStackTrace();
			}

		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new ShowIP();
			}
		});
	}
}

