package p1;

import java.util.Observable;

import javax.swing.Icon;



public class IconManager extends Observable{
	private Buffer<Icon> iconBuffer;
	private Thread thread;
	
	public IconManager(Buffer<Icon> iconBuffer){
		this.iconBuffer = iconBuffer;
	}
	
	public void start() {
		if(thread==null) {
			thread = new Worker();
			thread.start();
		}
	}

	private class Worker extends Thread{
			public void run(){
				while(!Thread.interrupted()) {
					try {
						setChanged();
						notifyObservers(iconBuffer.get());
						
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					
				}
				
				
			}
	}
	
}
