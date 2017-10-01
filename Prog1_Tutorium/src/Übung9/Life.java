package Übung9;

public class Life{
	private int rows;
	private int columns;
	private Stone[][] gameBoard;
	
	public Life(int rows, int columns){
		this.rows=rows;
		this.columns=columns;
		this.gameBoard=new Stone[rows][columns];
	}	
	
	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}

	public Stone[][] getGameBoard() {
		return gameBoard;
	}

	public void setGameBoard(Stone[][] gameBoard) {
		this.gameBoard = gameBoard;
	}

	public static void main(String args[]) {
		Life life = new Life(5,11);
		
		life.init();
		
		while(true){
			life.print();
			life.calc();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	private void init() {
		for (int i = 0; i < gameBoard.length; i++) {
			for (int j = 0; j < gameBoard[i].length; j++) {
				double random = Math.random();
				if (random > 0.5) {
					gameBoard[i][j] = new Stone(true);
				} else {
					gameBoard[i][j] = new Stone(false);
				}
			}
		}
		
	}

	private void print() {
		for (int i = 0; i < gameBoard.length; i++) {
			for (int j = 0; j < gameBoard[i].length; j++) {
				System.out.print(gameBoard[i][j].print());	
			}
			System.out.println();
		}

		System.out.println("____________________________________");
		System.out.println();
	}

	private void calc() {
		Stone[][] newGameBoard = new Stone[rows][columns];

		for (int i = 0; i < gameBoard.length; i++) {
			for (int j = 0; j < gameBoard[i].length; j++) {
				int counter = getNumberOfNeighbours(i, j);
				if (gameBoard[i][j].isAlive() && counter == 3) {
					newGameBoard[i][j]=new Stone(true, gameBoard[i][j].getRoundsAlive()+1);
				} else if (gameBoard[i][j].isAlive() && counter == 2) {
					newGameBoard[i][j]=new Stone(true, gameBoard[i][j].getRoundsAlive()+1);
				} else if (!gameBoard[i][j].isAlive() && counter == 3) {
					newGameBoard[i][j]=new Stone(true, gameBoard[i][j].getRoundsAlive()+1);
				} else if (counter > 3 || counter < 2) {
					newGameBoard[i][j] = new Stone(false);
				} else {
					newGameBoard[i][j] = new Stone(false);
				}
			}
		}

		for (int i = 0; i < newGameBoard.length; i++) {
			for (int j = 0; j < newGameBoard[i].length; j++) {
				gameBoard[i][j] = newGameBoard[i][j];
			}
		}
		
	}

	private int getNumberOfNeighbours( int i, int j) {
		int numberOfNeighbours = 0;
		if (gameBoard[(i + 1) % gameBoard.length][j].isAlive()) { // unten
			++numberOfNeighbours;
		}
		if (gameBoard[i][(j + 1) % gameBoard[i].length].isAlive()) { // rechts
			++numberOfNeighbours;
		}
		if (gameBoard[(i + 1) % gameBoard.length][(j + 1) % gameBoard[i].length].isAlive()) { // unten rechts
			++numberOfNeighbours;
		}
		if (gameBoard[(i - 1 + gameBoard.length) % (gameBoard.length)][(j - 1 + gameBoard[i].length) % (gameBoard[i].length)].isAlive()) { // oben links
			++numberOfNeighbours;
		}
		if (gameBoard[(i - 1 + gameBoard.length) % (gameBoard.length)][j].isAlive()) { // oben
			++numberOfNeighbours;
		}
		if (gameBoard[i][(j - 1 + gameBoard[i].length) % (gameBoard[i].length)].isAlive()) { // links
			++numberOfNeighbours;
		}
		if (gameBoard[(i + 1) % gameBoard.length][(j - 1 + gameBoard[i].length) % (gameBoard[i].length)].isAlive()) { // unten links
			++numberOfNeighbours;
		}
		if (gameBoard[(i - 1 + gameBoard.length) % (gameBoard.length)][(j + 1) % gameBoard[i].length].isAlive()) { // oben rechts
			++numberOfNeighbours;
		}
		return numberOfNeighbours;
	}

	@SuppressWarnings("unused")
	private int[][] getCoordinates( int x) {
		if (x >= 0) {
			int[][] coordinates = new int[1][2];
			return getCoordinates( x, 0, 0, coordinates);
		} else {
			return null;
		}
	}

	private int[][] getCoordinates( int x, int i, int j, int[][] coordinates) {
		if (i < gameBoard.length && j < gameBoard[i].length) {

			if (getNumberOfNeighbours( i, j) == x) {
				coordinates[coordinates.length - 1][0] = i;
				coordinates[coordinates.length - 1][1] = j;

				int[][] newCoordinates = new int[coordinates.length + 1][2];
				for (int i2 = 0; i2 < coordinates.length; i2++) {
					for (int j2 = 0; j2 < coordinates[i2].length; j2++) {
						newCoordinates[i2][j2] = coordinates[i2][j2];
					}
				}

				coordinates = newCoordinates;
			}
			boolean incrementedJ=false;
			if(j==gameBoard[i].length-1){
				i++;
				j=0;
				incrementedJ=true;
			}
			coordinates = getCoordinates(x, i,incrementedJ?j: j+1, coordinates);
		}

		return coordinates;
	}
}