package Übung1;

public class Sum {
	public static void main(String args[]) {
		int n = 6;
		int erg = 0;
		int i = 0;

		while (i < n) {
			erg = erg + i;
			i = i + 1;
			System.out.println(erg);
		}

		System.out.println("______________");

		n = 10;
		erg = 1;
		i = 1;
		while (i <= 10) {
			erg = erg * i;
			i = i + 1;
			System.out.println(erg);
		}
	}
}
