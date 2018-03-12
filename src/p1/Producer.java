package p1;

import javax.swing.Icon;

/**
 * 
 * Produces a iconbuffer from a iconproducerbuffer
 * @author johannes.roos
 *
 */

public class Producer{
	private Buffer<IconProducer> prodBuffer;
	private Buffer<Icon> iconBuffer;
	private Thread thread;

	/**
	 * 
	 * Constructor
	 * @param prodBuffer Incomming IconProducerbuffer
	 * @param iconBuffer Outgoing Iconbuffer
	 */
	public Producer(Buffer<IconProducer> prodBuffer, Buffer<Icon> iconBuffer){
		this.prodBuffer = prodBuffer;
		this.iconBuffer = iconBuffer;

	}
	/**
	 * Starts the worker
	 */
	public void start() {
		if(thread==null) {
			thread = new Worker();
			thread.start();
		}
	}

	/**
	 * Inner class, extends thread.
	 * Handles the buffers 
	 * @author johannes.roos
	 *
	 */
	private class Worker extends Thread {
		public void run() {
			IconProducer iconProducer;
			while(!Thread.interrupted()) {
				try {
					iconProducer = prodBuffer.get();
					for(int i = 0; i < iconProducer.times(); i++){
						for(int j = 0; j < iconProducer.size(); j++){
							iconBuffer.put(iconProducer.nextIcon());
							Thread.sleep(iconProducer.delay());
						}
					}
				} catch (InterruptedException e) {
					break;
				}
			}
		}
	}

}

