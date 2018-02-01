package p1;

public class IconProducerManager {
	private Buffer<IconProducer> buffer;

	public IconProducerManager(Buffer<IconProducer> buffer) {
		super();
		this.buffer = buffer;
	}

	public void addIconProducer(IconProducer ip){
		buffer.put(ip);
	}
	
}
