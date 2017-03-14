
public class Player {

	private final int MAX_POINTS = 50;
	private String name;
	private int points;
	
	Player(String aName){
		
		this.name = aName;	
	}
	
	public String getName(){
		return this.name;
	}
	
	public int getPoints(){
		return this.points;
	}
	
	public int setPoints(){
		this.points += points;
		if (this.points > MAX_POINTS){
			this.points = 25;
		}
		return this.points;
	}
}
