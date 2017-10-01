package Übung9;

public class Stone {
	private boolean alive;
	private int roundsAlive;
	private int neighbours;
	
	public Stone(boolean alive){
		this.alive=alive;
		this.roundsAlive= alive ? 1:0;
	}
	
	public Stone(boolean alive, int roundsAlive){
		this.alive=true;
		this.roundsAlive=roundsAlive;
	}
	
	public Stone(){
		this.alive=false;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public int getNeighbours() {
		return neighbours;
	}

	public void setNeighbours(int neighbours) {
		this.neighbours = neighbours;
	}
	
	public int getRoundsAlive() {
		return roundsAlive;
	}

	public void setRoundsAlive(int roundsAlive) {
		this.roundsAlive = roundsAlive;
	}
	
	public String print(){
		switch(roundsAlive){
		case 0:
			return " ";
		case 1:
			return ".";
		case 2:
			return "o";
		case 3:
			return "O";
		default:
			return "*";
		}
	}
	
	public void countNeighbours(){
		
	}
	
	public boolean computeNext(){
		return false;
	}
	
}
