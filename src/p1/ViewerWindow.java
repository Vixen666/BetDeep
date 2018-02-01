package p1;

import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class ViewerWindow {
	public ViewerWindow(Viewer viewer, int x, int y) {
		JFrame frame = new JFrame("Viewer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new FlowLayout(FlowLayout.CENTER));
		frame.add(viewer);
		frame.pack();
		frame.setLocation(x,y);
		frame.setVisible(true);
	}
	
	// example ViewerWindow, Viewer
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run() {
				Viewer v = new Viewer(600,400);
				ViewerWindow window = new ViewerWindow(v,50,100);
				v.setIcon(new ImageIcon("images/orkanen.jpg"));
			}
		});
	}
}
