package p1;

import java.io.IOException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class MainDS1 {
    private static Icon[] getIconArray() {
		Icon[] res = {new ImageIcon("images/new1.jpg"),
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
    
    // Använd denna main-metod när du är färdig med IconProducerManager
//    public static void main(String[] args) {
//        Buffer<IconProducer> producerBuffer    = new Buffer<IconProducer>();
//                
//        IconProducerManager ipManager = new IconProducerManager(producerBuffer);  
//        ipManager.addIconProducer(new ArrayProducer(getIconArray(),1000,2));
//        
//        TestDS1 testDs1 = new TestDS1(producerBuffer);
//        testDs1.start();
//    }
    
    // Använd denna main-metod när du är färdig med IconProducerManager + FileProducer
    public static void main(String[] args) throws IOException {
        Buffer<IconProducer> producerBuffer = new Buffer<IconProducer>();
                
        IconProducerManager ipManager = new IconProducerManager(producerBuffer);        
        ipManager.addIconProducer(new FileProducer("files/new.txt"));
        
        TestDS1 testDs1 = new TestDS1(producerBuffer);
        testDs1.start();
    }
    
}
