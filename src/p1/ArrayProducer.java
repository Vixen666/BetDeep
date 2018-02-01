package p1;

import javax.swing.Icon;

public class ArrayProducer implements IconProducer {
	private Icon[] icons;
	private int delay = 0;
	private int times = 0;
	private int currentIndex = -1;
	
	public ArrayProducer(Icon[] icons, int delay, int times) {
		this.delay = delay;
		this.times = times;
		this.icons = icons;
	}
	
	@Override
	public int delay() {
		return delay;
	}

	@Override
	public int times() {
		return times;
	}

	@Override
	public int size() {
		return (icons==null) ? 0 : icons.length;
	}

	@Override
	public Icon nextIcon() {
		if(icons==null || icons.length==0)
		    return null;
		currentIndex = (currentIndex+1) % icons.length;
		return icons[currentIndex];
	}
}
