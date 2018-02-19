package f8;

import java.util.HashMap;
import java.util.Iterator;

public class ServerController {
	private HashMap<String,String> map = new HashMap<String,String>();
	
	public synchronized String put(String name, String age) {
		String response = "";
		if(name.length()>0 && age.length()>0) {
    		String person = name + "," + age;
	    	response = map.put(name, person);
		    if(response==null) {
			    response = person + " lagrad";
		    } else {
			    response += " ersatt med " + person;
		    }
		} else {
			response = "bad argument: name="+name+", age="+age;
		}
		return response;
	}
	
	public synchronized String get(String name) {
		String response = map.get(name);
		if(response==null) {
			response = name + " är okänd";
		}
		return response;
	}

	public synchronized String list() {
		Iterator<String> iter = map.keySet().iterator();
		String response = "";
		while(iter.hasNext()) {
			response += map.get(iter.next()) + "\n";
		}
		return response;
	}
	
	public synchronized String remove(String name) {
		String response = map.remove(name);
		if(response==null) {
			response = name + " är okänd";
		}
		return response;
	}
	
	public static void main(String[] args) {
		ServerController controller = new ServerController();
		AgeServerA serverA = new AgeServerA(controller,3440);
//		AgeServerB serverB = new AgeServerB(controller,3441);
//		AgeServerC serverC = new AgeServerC(controller,3442);
//		AgeServerD serverD = new AgeServerD(controller,3443);
	}
}
