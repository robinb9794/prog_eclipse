package Übung11;

public class Stone implements Disease{
	private boolean alwaysStone;
	private boolean neverStone;
	private boolean alive;
	private int roundsAlive;
	private int numberOfNeighbours;
	private Stone[] neighbours;

	public Stone(boolean alive) {
		this.alive = alive;
		this.roundsAlive = alive ? 1 : 0;
	}

	public Stone(boolean alwaysStone, boolean neverStone) {
		this.alwaysStone = alwaysStone;
		this.neverStone = neverStone;
		this.alive = alwaysStone ? true : false;
		this.roundsAlive = alive || alwaysStone ? 1 : 0;
	}

	public Stone(boolean alwaysStone, boolean neverStone, int roundsAlive) {
		this.alwaysStone = alwaysStone;
		this.neverStone = neverStone;
		this.alive = alwaysStone ? true : false;
		this.roundsAlive = roundsAlive;
	}

	public Stone(boolean alive, int roundsAlive) {
		this.alive = true;
		this.roundsAlive = roundsAlive;
	}

	public Stone() {
		this.alive = false;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public int getNumberOfNeighbours() {
		return numberOfNeighbours;
	}

	public void setNumberOfNeighbours(int neighbours) {
		this.numberOfNeighbours = neighbours;
	}

	public int getRoundsAlive() {
		return roundsAlive;
	}

	public void setRoundsAlive(int roundsAlive) {
		this.roundsAlive = roundsAlive;
	}

	public String print() {
		switch (roundsAlive) {
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

	public void countNeighbours() {
		this.numberOfNeighbours = 0;
		for (int i = 0; i < neighbours.length; i++) {
			if (neighbours[i].isAlive()) {
				++this.numberOfNeighbours;
			}
		}
	}

	public boolean computeNext() {
		return false;
	}

	public void setNeighbours(Stone[] neighbours) {
		this.neighbours = neighbours;
	}

	public Stone[] getNeighbours() {
		return this.neighbours;
	}

	public boolean isAlwaysStone() {
		return alwaysStone;
	}

	public void setAlwaysStone(boolean alwaysStone) {
		this.alwaysStone = alwaysStone;
	}

	public boolean isNeverStone() {
		return neverStone;
	}

	public void setNeverStone(boolean neverStone) {
		this.neverStone = neverStone;
	}

	@Override
	public double chanceOfDisease() {
		return Math.random();
	}

	@Override
	public double chanceOfInfection() {
		return Math.random();
	}

}
