package p1;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class P2Viewer extends JPanel implements IconClientCallback{
	private JLabel lblIcon = new JLabel();
	
	public P2Viewer(IconClient iconClient, int width, int height) {
		setLayout(new FlowLayout(FlowLayout.CENTER));
		lblIcon.setOpaque(true);
		add(lblIcon);
		setPreferredSize(new Dimension(width,height));
		iconClient.addProgressListener(this);
	}

	public void setIcon(Icon icon) {
		lblIcon.setIcon(icon);
	}

	@Override
	public void notifyListeners(Icon icon) {
		setIcon(icon);
	}
}
