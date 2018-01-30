package dataGathering;

public class Game {
	private Team homeTeam;
	private Team awayTeam;
	
	private int homeGoals;
	private int awayGoals;
	
	public Game(Team homeTeam, Team awayTeam, int homeGoals, int awayGoals) {
		super();
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.homeGoals = homeGoals;
		this.awayGoals = awayGoals;
		homeTeam.increaseGoals(homeGoals);
		homeTeam.increaseGoalsConceded(awayGoals);
		
		awayTeam.increaseGoals(awayGoals);
		awayTeam.increaseGoalsConceded(homeGoals);
		if(homeGoals > awayGoals) {
			homeTeam.increasePoints(3);
			homeTeam.increasePointsHome(3);
		}else if(homeGoals < awayGoals) {
			awayTeam.increasePoints(3);
			awayTeam.increasePointsAway(3);
		}else {
			homeTeam.increasePoints(1);
			homeTeam.increasePointsHome(1);
			
			awayTeam.increasePoints(1);
			awayTeam.increasePointsAway(1);
		}
		

	}

	public Team getHomeTeam() {
		return homeTeam;
	}

	public Team getAwayTeam() {
		return awayTeam;
	}

	public int getHomeGoals() {
		return homeGoals;
	}

	public int getAwayGoals() {
		return awayGoals;
	}
	
	public String toString() {
		return this.homeTeam + " - " +this.awayTeam +"\t" + this.homeGoals +" - " + this.awayGoals; 
	}
	

}
