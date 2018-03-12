package p1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * Reads a file from harddrive and produces a array of icons
 * @author johannes.roos
 *
 */
public class FileProducer implements IconProducer {
	private ArrayList<Icon> icons;
	private int delay = 0;
	private int times = 0;
	private int currentIndex = -1;
	
	/**
	 * Reads a file in a specific way.
	 * First row nbrOfTimes
	 * Second row delay
	 * The rest filenames for img-files for gif
	 * @param filename File to be read
	 * @throws IOException
	 */
	public FileProducer(String filename) throws IOException {
		this.icons = new ArrayList<Icon>();
		try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF-8"))){
			this.times = Integer.parseInt(br.readLine());
			this.delay = Integer.parseInt(br.readLine());
			String line =""; 
			while(line != null){
				line = br.readLine();
				if(line != null){
					this.icons.add(new ImageIcon(line));		
				}		
			}
		}
	}
	
	/**
	 * return the delay
	 */
	public int delay() {
		return delay;
	}

	/**
	 * returns nbroftimes
	 */
	@Override
	public int times() {
		return times;
	}

	/**
	 * returns size
	 */
	@Override
	public int size() {
		return icons.size();
	}

	/**
	 * returns nexticon, starts over if reached the end
	 */
	@Override
	public Icon nextIcon() {
		if(icons==null || icons.size()==0)
		    return null;
		currentIndex = (currentIndex+1) % icons.size();
		return icons.get(currentIndex);
	}

}
