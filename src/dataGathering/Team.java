package dataGathering;

public class Team implements Comparable<Team>{
	private int gameWeek;
	private String name;
	private int id;
	private String code;
	private int totalPoints;
	private int totalGoals;
	private int totalGoalsConceded;
	
	private int[] goals;
	private int[] goalsConceded;
	
	private int[] points;
	private int[] pointsHome;
	private int[] poinstAway;
	
	private int[] prevGamePoints;
	private int[] prevNGamesPoints;
	
	private int[] ranking;
	
	private int[] goalsForLastN;
	private int[] goalsAgainstLastN;
	
	public Team(String name, String code, int id, int numberOfMatches) {
		super();
		this.gameWeek = 0;
		this.totalPoints = 0;
		this.name = name;
		this.id = id;
		this.code = code;
		this.goals = new int[numberOfMatches];
		this.goalsConceded = new int[numberOfMatches];

		this.points= new int[numberOfMatches];
		this.poinstAway= new int[numberOfMatches];
		this.pointsHome= new int[numberOfMatches];
		this.ranking = new int[numberOfMatches];
		
	}
	public String getName() {
		return name;
	}
	
	public int getId() {
		return id;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setRank(int rank) {
		this.ranking[gameWeek] = rank;
	}
	
	public void increaseGoals(int goals) {
		this.goals[gameWeek] += goals;
	}

	public void increaseGoalsConceded(int goals) {
		this.goalsConceded[gameWeek] += goals;
	}
	
	public void increasePoints(int points) {
		this.points[gameWeek] += points;
	}
	
	public void increasePointsHome(int points) {
		this.pointsHome[gameWeek] += points;
	}
	
	public void increasePointsAway(int points) {
		this.poinstAway[gameWeek] += points;
	}
	
	public void increaseGameWeek() {
		totalPoints();
		totalGoalsConceded();
		totalGoals();
		this.gameWeek++;
	}
	
	public void totalPoints() {
		int sum = 0;
		for(int i: points) {
			sum += i;
		}
		this.totalPoints = sum;
	}
	
	public void totalGoals() {
		int sum = 0;
		for(int i: goals) {
			sum += i;
		}
		this.totalGoals = sum;
	}
	
	public void totalGoalsConceded() {
		int sum = 0;
		for(int i: goalsConceded) {
			sum += i;
		}
		this.totalGoalsConceded = sum;
	}
	
	public String toString() {
		return String.format("%-5d%-25s%-3s%5d",ranking[gameWeek], name, code, totalPoints);

	}
	
	public int compareTo(Team t) {
		if(this.totalPoints < t.totalPoints) {
			return 1;
		}if (this.totalPoints > t.totalPoints) {
			return -1;
		}if (this.totalGoals - this.totalGoalsConceded < t.totalGoals-t.totalGoalsConceded) {
			return 1;
		}return 1;
		
	}
}
