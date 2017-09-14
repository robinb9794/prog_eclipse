package Sortieren;

public class ShellSort {
	static void shellSort(int[] a) {
		int iDist = 1;
		for (; iDist <= a.length / 9; iDist = 3 * iDist + 1) {
		}
		for (; iDist > 0; iDist /= 3) {
			for (int i1 = iDist; i1 < a.length; i1++) {
				final int IVAL = a[i1];
				int i2 = i1;
				while (i2 >= iDist && a[i2 - iDist] > IVAL) {
					a[i2] = a[i2 - iDist];
					i2 = i2 - iDist;
				}
				a[i2] = IVAL;
				for (int i = 0; i < a.length; i++)
					System.out.print(a[i] + "\t");
				System.out.println();
			}
		}
	}

	public static void main(String args[]) {
		int[] a = { 5, 8, 10, 2, 9, 7, 4, 1, 3, 6 };
		shellSort(a);
	}
}
