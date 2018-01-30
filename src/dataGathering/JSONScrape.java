package dataGathering;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.text.html.parser.Parser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

public class JSONScrape  {
	String content;
	JSONObject obj;
	JSONArray arr ;
	
	private int gamesPlayed = 0; 
	private ArrayList<Game> games = new ArrayList<Game>();
	private ArrayList<Team> teams = new ArrayList<Team>();
	private ArrayList<GameWeek> gameWeeks = new ArrayList<GameWeek>();


	public JSONScrape() throws IOException, JSONException {
		//content = new String(Files.readAllBytes(Paths.get("C:\\Users\\Vixen666\\eclipse-workspace\\JSON\\src\\dataGathering\\en.json")));
		//obj = new JSONObject(content)  ;
		obj = readJSONfromURL("https://raw.githubusercontent.com/opendatajson/football.json/master/2016-17/en.1.json");
		arr = obj.getJSONArray("rounds");
	}

	public void scrape() throws JSONException, IOException {
		addStart("team1");
		addStart("team2");	
		
		for(int i = 0; i< arr.length(); i++) {
			for(int j = 0; j< arr.getJSONObject(i).getJSONArray("matches").length(); j++) {
				games.add(new Game(findTeam("team1", i,j), findTeam("team2",i,j), findGoals("score1",i,j), findGoals("score2",i,j)));
				gamesPlayed++;
			}		
			System.out.println("---------------");
			System.out.println("Runda:    "+i);
			Collections.sort(teams);
			for(int k = 0; k < 20; k++) {
				
				teams.get(k).setRank(k+1);
				System.out.println(teams.get(k).toString());
				teams.get(k).increaseGameWeek();
			}
		}
		
		System.out.println("BreakPoint, SPARA");
	}

	public Team findTeam(String homeOrAway, int roundNbr, int matchNbr) throws JSONException {
		for(int i = 0; i< teams.size(); i++) {
			if(arr.getJSONObject(roundNbr).getJSONArray("matches").getJSONObject(matchNbr).getJSONObject(homeOrAway).getString("code").toUpperCase().equals(teams.get(i).getCode())) {
				return teams.get(i);
			}
		}
		return null;
	}

	
	public int findGoals(String score1OrScore2, int roundNbr, int matchNbr) throws JSONException {
		return arr.getJSONObject(roundNbr).getJSONArray("matches").getJSONObject(matchNbr).getInt(score1OrScore2);
	}

	
	public void addStart(String team) throws JSONException {
		for(int i = 0; i < 10; i++) {
			String name = arr.getJSONObject(0).getJSONArray("matches").getJSONObject(i).getJSONObject(team).getString("name");
			String code = arr.getJSONObject(0).getJSONArray("matches").getJSONObject(i).getJSONObject(team).getString("code").toUpperCase();

			teams.add(new Team(name, code, i, 38));
			System.out.println(teams.get(i).toString());
		}
	}
	
	public static JSONObject readJSONfromURL(String url) throws MalformedURLException, IOException, JSONException {
		InputStream is = new URL(url).openStream();
		try(BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")))) {
			   String jsonText = readAll(rd);
			      JSONObject json = new JSONObject(jsonText);
			      return json;
		}
	}
	
	 private static String readAll(Reader rd) throws IOException {
		    StringBuilder sb = new StringBuilder();
		    int cp;
		    while ((cp = rd.read()) != -1) {
		      sb.append((char) cp);
		    }
		    return sb.toString();
		  }

	public static void main(String[] args) throws JSONException, IOException {
		JSONScrape js = new JSONScrape();
		js.scrape();
	}

}
