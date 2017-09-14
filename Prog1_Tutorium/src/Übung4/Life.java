package Übung4;

public class Life {
	public static void main(String args[]) {
		int rows = 5;
		int columns = 11;

		boolean[][] gameBoard = new boolean[rows][columns];

		int mod = 2;

		int i = 0;
		int j = 0;

		while (i < rows) {
			while (j < columns) {
				if (j % mod == 0) {
					gameBoard[i][j] = true;
				} else {
					gameBoard[i][j] = false;
				}
				j = j + 1;
			}
			mod = mod + 1;
			i = i + 1;
			j = 0;
		}

		i = 0;
		j = 0;

		while (i < rows) {
			while (j < columns) {
				if (gameBoard[i][j] == true) {
					System.out.print("X");
				} else {
					System.out.print("_");
				}
				j = j + 1;
			}
			i = i + 1;
			j = 0;
			System.out.println();
		}
	}
}
