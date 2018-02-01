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

public class FileProducer implements IconProducer {
	private ArrayList<Icon> icons;
	private int delay = 0;
	private int times = 0;
	private int currentIndex = -1;
	
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
	
	public int delay() {
		return delay;
	}

	@Override
	public int times() {
		return times;
	}

	@Override
	public int size() {
		return icons.size();
	}

	@Override
	public Icon nextIcon() {
		if(icons==null || icons.size()==0)
		    return null;
		currentIndex = (currentIndex+1) % icons.size();
		return icons.get(currentIndex);
	}

}
