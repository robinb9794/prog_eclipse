package �bung5;

public class Life {
	public static void main(String args[]) {
		int rows = 5;
		int columns = 11;

		boolean[][] gameBoard = new boolean[rows][columns];

		int mod = 2;

		for (int i = 0; i < gameBoard.length; i++) {
			for (int j = 0; j < gameBoard[i].length; j++) {
				if (j % mod == 0) {
					gameBoard[i][j] = true;
				} else {
					gameBoard[i][j] = false;
				}
			}
			mod = mod + 1;
		}

		for (int i = 0; i < gameBoard.length; i++) {
			for (int j = 0; j < gameBoard[i].length; j++) {
				if (gameBoard[i][j] == true) {
					System.out.print("X");
				} else {
					System.out.print("_");
				}
			}
			mod = mod + 1;
			System.out.println();
		}

		System.out.println();

		boolean[][] newGameBoard = new boolean[rows][columns];

		for (int i = 0; i < gameBoard.length; i++) {
			for (int j = 0; j < gameBoard[i].length; j++) {
				int counter = 0;
				if (gameBoard[(i + 1) % gameBoard.length][j] == true) { // unten
					++counter;
				}
				if (gameBoard[i][(j + 1) % gameBoard[i].length] == true) { // rechts
					++counter;
				}
				if (gameBoard[(i + 1) % gameBoard.length][(j + 1) % gameBoard[i].length] == true) { // unten rechts
					++counter;
				}
				if (gameBoard[(i - 1 + gameBoard.length) % (gameBoard.length)][(j - 1 + gameBoard[i].length) % (gameBoard[i].length)] == true) { // oben links
					++counter;
				}
				if (gameBoard[(i - 1 + gameBoard.length) % (gameBoard.length)][j] == true) { // oben
					++counter;
				}
				if (gameBoard[i][(j - 1 + gameBoard[i].length) % (gameBoard[i].length)] == true) { // links
					++counter;
				}
				if (gameBoard[(i + 1) % gameBoard.length][(j - 1 + gameBoard[i].length) % (gameBoard[i].length)] == true) { // unten links
					++counter;
				}
				if (gameBoard[(i - 1 + gameBoard.length) % (gameBoard.length)][(j + 1) % gameBoard[i].length] == true) { // oben rechts
					++counter;
				}
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
				if (newGameBoard[i][j] == true) {
					System.out.print("X");
				} else {
					System.out.print("_");
				}
			}
			System.out.println();
		}
	}
}