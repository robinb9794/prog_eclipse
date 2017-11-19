package Aufgabe_4;

public class CountBits {
	public static void main(String args[]) {
		count(9);
	}

	public static void count(int n) {
		int xor = 1, count_0 = 0, count_1 = 0;

		for (int i = 0; i < 32 - Integer.numberOfLeadingZeros(n); i++) {
			if ((n ^ xor) < n) {
				++count_1;
			} else {
				++count_0;
			}
			xor = xor << 1;
		}

		System.out.println("Die Zahl " + n + ": [" + count_0 + "] x die Null, [" + count_1 + "] x die Eins.\nTest:");
		System.out.print(Integer.toBinaryString(n));
	}
}
