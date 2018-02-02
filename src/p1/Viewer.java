package p1;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Viewer extends JPanel implements Observer {
	private JLabel lblIcon = new JLabel();
	
	public Viewer(IconManager iconManager, int width, int height) {
		setLayout(new FlowLayout(FlowLayout.CENTER));
		lblIcon.setOpaque(true);
		add(lblIcon);
		setPreferredSize(new Dimension(width,height));
		iconManager.addObserver(this);
	}

	public void setIcon(Icon icon) {
		lblIcon.setIcon(icon);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		Icon icon = (Icon)arg1;
		this.setIcon(icon);		
	}
}
