package Sortieren;

public class MergeSort {
	public static void main(String args[]) {
		int[] field = { 8, 7, 6, 5, 4, 3, 2, 1 };
		merge(field);
	}

	static void merge(int[] field) {
		merge_help(field, 0, field.length - 1);
	}

	private static void merge_help(int[] field, int iLeft, int iRight) {
		if (iLeft < iRight) {
			final int MIDDLE = (iLeft + iRight) / 2;
			merge_help(field, iLeft, MIDDLE);
			merge_help(field, MIDDLE + 1, iRight);

			int[] tmp = new int[iRight - iLeft + 1];

			for (int i = iLeft; i <= MIDDLE; ++i) {
				tmp[i - iLeft] = field[i];
			}
			for (int i = MIDDLE + 1; i <= iRight; ++i) {
				tmp[tmp.length - i + MIDDLE] = field[i];
			}
			int iL = 0;
			int iR = tmp.length - 1;
			for (int i = iLeft; i <= iRight; ++i) {
				field[i] = tmp[iL] <= tmp[iR] ? tmp[iL++] : tmp[iR--];
			}
			for (int i = 0; i < field.length; ++i) {
				System.out.print(field[i] + "\t");
			}
			System.out.println();
		}
	}
}
