package p1;

import javax.swing.Icon;

public class TestDS1 extends Thread {
	private Buffer<IconProducer> buffer;
	
	public TestDS1(Buffer<IconProducer> buffer) {
		this.buffer = buffer;
	}
	
	public void run() {
		IconProducer producer;
		while(!Thread.interrupted()) {
			try {
				producer = buffer.get();
				populateProducerToConsole(producer);
			} catch (InterruptedException e) {
				break;
			}
		}
	}

	private void populateProducerToConsole(IconProducer producer) throws InterruptedException {
		Icon icon;
	    for(int times=0; times<producer.times(); times++) {
	    	for(int index = 0; index<producer.size(); index++) {
	    		icon = producer.nextIcon();
	    		System.out.println(icon.toString() + ", w=" + icon.getIconWidth() + ", h=" + icon.getIconHeight());
	    		Thread.sleep(producer.delay());
	    	}
	    }
	}
	private void populateProducerToViewer(IconProducer producer) throws InterruptedException {
		Viewer v = new Viewer(300,400);
		new ViewerWindow(v,100,100);
		for(int times=0; times<producer.times(); times++) {
	    	for(int index = 0; index<producer.size(); index++) {
	    		v.setIcon(producer.nextIcon());
	    		System.out.println(producer.nextIcon().toString());
	    		Thread.sleep(producer.delay());
	    	}
	    }
	}
}
