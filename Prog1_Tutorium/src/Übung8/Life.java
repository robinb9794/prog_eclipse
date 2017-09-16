package Übung8;

public class Life{
	private int rows;
	private int columns;
	private int[][] gameBoard;
	
	public Life(int rows, int columns){
		this.rows=rows;
		this.columns=columns;
		this.gameBoard=new int[rows][columns];
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

	public int[][] getGameBoard() {
		return gameBoard;
	}

	public void setGameBoard(int[][] gameBoard) {
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
					gameBoard[i][j] = 1;
				} else {
					gameBoard[i][j] = 0;
				}
			}
		}
		
	}

	private void print() {
		for (int i = 0; i < gameBoard.length; i++) {
			for (int j = 0; j < gameBoard[i].length; j++) {
				switch(gameBoard[i][j]){
				case 0:
					System.out.print(" ");
					break;
				case 1:
					System.out.print(".");
					break;
				case 2:
					System.out.print("o");
					break;
				case 3:
					System.out.print("O");
					break;
				default:
					System.out.print("*");
					break;
				}				
			}
			System.out.println();
		}

		System.out.println();
		System.out.println();
	}

	private void calc() {
		int[][] newGameBoard = new int[rows][columns];

		for (int i = 0; i < gameBoard.length; i++) {
			for (int j = 0; j < gameBoard[i].length; j++) {
				int counter = getNumberOfNeighbours(i, j);
				if (gameBoard[i][j] >0 && counter == 3) {
					newGameBoard[i][j]=++gameBoard[i][j];
				} else if (gameBoard[i][j] >0 && counter == 2) {
					newGameBoard[i][j]=++gameBoard[i][j];
				} else if (gameBoard[i][j] == 0 && counter == 3) {
					newGameBoard[i][j]=++gameBoard[i][j];
				} else if (counter > 3 || counter < 2) {
					newGameBoard[i][j] = 0;
				} else {
					newGameBoard[i][j] = 0;
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
		if (gameBoard[(i + 1) % gameBoard.length][j] >0) { // unten
			++numberOfNeighbours;
		}
		if (gameBoard[i][(j + 1) % gameBoard[i].length] >0) { // rechts
			++numberOfNeighbours;
		}
		if (gameBoard[(i + 1) % gameBoard.length][(j + 1) % gameBoard[i].length] >0) { // unten rechts
			++numberOfNeighbours;
		}
		if (gameBoard[(i - 1 + gameBoard.length) % (gameBoard.length)][(j - 1 + gameBoard[i].length) % (gameBoard[i].length)] >0) { // oben links
			++numberOfNeighbours;
		}
		if (gameBoard[(i - 1 + gameBoard.length) % (gameBoard.length)][j] >0) { // oben
			++numberOfNeighbours;
		}
		if (gameBoard[i][(j - 1 + gameBoard[i].length) % (gameBoard[i].length)]>0) { // links
			++numberOfNeighbours;
		}
		if (gameBoard[(i + 1) % gameBoard.length][(j - 1 + gameBoard[i].length) % (gameBoard[i].length)] >0) { // unten links
			++numberOfNeighbours;
		}
		if (gameBoard[(i - 1 + gameBoard.length) % (gameBoard.length)][(j + 1) % gameBoard[i].length] >0) { // oben rechts
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