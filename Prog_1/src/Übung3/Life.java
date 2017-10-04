package Übung3;

public class Life {
	public static void main(String args[]) {
		int rows = 5;
		int columns = 11;
		int mod = 2;

		int i = 0;
		int j = 0;

		while (i < rows) {
			while (j < columns) {
				if (j % mod == 0) {
					System.out.print("X");
				} else {
					System.out.print("_");
				}
				j = j + 1;
			}
			mod = mod + 1;
			i = i + 1;
			j = 0;
			System.out.println();
		}
	}
}
