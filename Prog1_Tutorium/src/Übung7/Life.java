package Übung7;

public class Life {
	public static int rows = 5;
	public static int columns = 11;
	public static boolean[][] gameBoard = new boolean[rows][columns];

	public static void main(String args[]) {
		init();
		calc();
		print();
		int[][] coordinates = getCoordinates(4);
		for (int i = 0; i < coordinates.length; i++) {
			for (int j = 0; j < coordinates[i].length; j++) {
				System.out.print(coordinates[i][j] + " ");
			}
			System.out.println();
		}
		// while (true) {
		// print();
		// calc();
		// try {
		// Thread.sleep(1000);
		// } catch (InterruptedException e) {
		// }
		// }
	}

	public static void init() {
		for (int i = 0; i < gameBoard.length; i++) {
			for (int j = 0; j < gameBoard[i].length; j++) {
				double random = Math.random();
				if (random > 0.5) {
					gameBoard[i][j] = true;
				} else {
					gameBoard[i][j] = false;
				}
			}
		}
	}

	public static void print() {
		for (int i = 0; i < gameBoard.length; i++) {
			for (int j = 0; j < gameBoard[i].length; j++) {
				if (gameBoard[i][j] == true) {
					System.out.print("X");
				} else {
					System.out.print("_");
				}
			}
			System.out.println();
		}

		System.out.println();
		System.out.println();
	}

	public static void calc() {
		boolean[][] newGameBoard = new boolean[rows][columns];

		for (int i = 0; i < gameBoard.length; i++) {
			for (int j = 0; j < gameBoard[i].length; j++) {
				int counter = getNumberOfNeighbours(i, j);
				if (gameBoard[i][j] == true && counter == 3) {
					newGameBoard[i][j] = true;
				} else if (gameBoard[i][j] == true && counter == 2) {
					newGameBoard[i][j] = true;
				} else if (gameBoard[i][j] == false && counter == 3) {
					newGameBoard[i][j] = true;
				} else if (counter > 3 || counter < 2) {
					newGameBoard[i][j] = false;
				} else {
					newGameBoard[i][j] = false;
				}
			}
		}

		for (int i = 0; i < newGameBoard.length; i++) {
			for (int j = 0; j < newGameBoard[i].length; j++) {
				gameBoard[i][j] = newGameBoard[i][j];
			}
		}
	}

	public static int getNumberOfNeighbours(int i, int j) {
		int numberOfNeighbours = 0;
		if (gameBoard[(i + 1) % gameBoard.length][j] == true) { // unten
			++numberOfNeighbours;
		}
		if (gameBoard[i][(j + 1) % gameBoard[i].length] == true) { // rechts
			++numberOfNeighbours;
		}
		if (gameBoard[(i + 1) % gameBoard.length][(j + 1) % gameBoard[i].length] == true) { // unten rechts
			++numberOfNeighbours;
		}
		if (gameBoard[(i - 1 + gameBoard.length) % (gameBoard.length)][(j - 1 + gameBoard[i].length) % (gameBoard[i].length)] == true) { // oben links
			++numberOfNeighbours;
		}
		if (gameBoard[(i - 1 + gameBoard.length) % (gameBoard.length)][j] == true) { // oben
			++numberOfNeighbours;
		}
		if (gameBoard[i][(j - 1 + gameBoard[i].length) % (gameBoard[i].length)] == true) { // links
			++numberOfNeighbours;
		}
		if (gameBoard[(i + 1) % gameBoard.length][(j - 1 + gameBoard[i].length) % (gameBoard[i].length)] == true) { // unten links
			++numberOfNeighbours;
		}
		if (gameBoard[(i - 1 + gameBoard.length) % (gameBoard.length)][(j + 1) % gameBoard[i].length] == true) { // oben rechts
			++numberOfNeighbours;
		}
		return numberOfNeighbours;
	}

	public static int[][] getCoordinates(int x) {
		if (x >= 0) {
			int[][] coordinates = new int[1][2];
			return getCoordinates(x, 0, 0, coordinates);
		} else {
			return null;
		}
	}

	public static int[][] getCoordinates(int x, int i, int j, int[][] coordinates) {
		if (i < gameBoard.length && j < gameBoard[i].length) {
			if (getNumberOfNeighbours(i, j) == x) {
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
			coordinates = getCoordinates(x, i, j + 1, coordinates);
			coordinates = getCoordinates(x, i + 1, 0, coordinates);
		}

		return coordinates;
	}
}