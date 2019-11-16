
package ohtu;

public class Player {
	
	private String name;
	private String birthdate;
	private String team;
	private String nationality;
    
    private int goals;
    private int assists;
    private int penalties;
    
    // GSON can skip this with sun.misc.Unsafe
	public Player(String name, String birthdate, String team, String nationality, int goals, int assists,
			int penalties) {
		this.name = name;
		this.birthdate = birthdate;
		this.team = team;
		this.nationality = nationality;
		this.goals = goals;
		this.assists = assists;
		this.penalties = penalties;
	}
	
	public String getName() {
		return name;
	}
	
	public String getBirthdate() {
		return birthdate;
	}
	
	public String getTeam() {
		return team;
	}
	
	public String getNationality() {
		return nationality;
	}
	
	public int getGoals() {
		return goals;
	}
	
	public int getAssists() {
		return assists;
	}
	
	public int getPoints() {
		return goals + assists;
	}
	
	public int getPenalties() {
		return penalties;
	}
	
	@Override
	public String toString() {
		return name + " team " + team + " goals " + goals + " assists " + assists;
	}
}
