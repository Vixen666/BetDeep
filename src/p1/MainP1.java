package p1;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class MainP1 {
	public static void main(String[] args) {
		Buffer<Icon> iconBuffer = new Buffer<Icon>();
		Buffer<IconProducer> producerBuffer	= new Buffer<IconProducer>();
		
		IconManager iconManager = new IconManager(iconBuffer);
		new ViewerWindow( new Viewer(iconManager,480,360) ,100,100 );
		new ViewerWindow( new Viewer(iconManager,320,320) ,500,100 );
		iconManager.start();
		
		Producer producer = new Producer(producerBuffer,iconBuffer);
		producer.start();
		
		IconProducerManager ipManager = new IconProducerManager(producerBuffer);		
		ipManager.addIconProducer(new ArrayProducer(getIconArray(),50,10));
		ipManager.addIconProducer(new ShowGubbe(3000));
		ipManager.addIconProducer(new FileProducer("files/new.txt"));
	}
	
	private static Icon[] getIconArray() {
		Icon[] res = {new ImageIcon("files/new1.jpg"),
				new ImageIcon("images/new2.jpg"),
				new ImageIcon("images/new3.jpg"),
				new ImageIcon("images/new4.jpg"),
				new ImageIcon("images/new5.jpg"),
				new ImageIcon("images/new6.jpg"),
				new ImageIcon("images/new7.jpg"),
				new ImageIcon("images/new8.jpg"),
				new ImageIcon("images/new9.jpg"),
				new ImageIcon("images/new10.jpg")};
		return res;
	}
}


class ShowGubbe implements IconProducer {
	private int delay;
	
	public ShowGubbe(int delay) {
		this.delay = delay;
	}

	@Override
	public int delay() {
		return delay;
	}

	@Override
	public int times() {
		return 1;
	}

	@Override
	public int size() {
		return 1;
	}

	@Override
	public Icon nextIcon() {
		return new ImageIcon("images/gubbe.jpg");
	}		
}
