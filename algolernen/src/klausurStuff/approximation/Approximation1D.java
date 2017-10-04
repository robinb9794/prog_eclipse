package klausurStuff.approximation;

public class Approximation1D {

	int[] werte;

	public Approximation1D(int[] werte) {
		this.werte = werte;
		java.util.Arrays.sort(this.werte);
	}

	public int approximate1d(int gesucht, int linkerRand, int rechterRand) {
		int iL = linkerRand;
		int iR = rechterRand;
		int MIDDLE = 0;
		while (iL <= iR) {
			MIDDLE = (iL + iR) / 2;
			final int RES = Integer.compare(werte[MIDDLE], gesucht);
			if (RES == 0) {
				return MIDDLE;
			} else if (RES < 0)
				iL = MIDDLE + 1;
			else
				iR = MIDDLE - 1;
		}

		if (gesucht < werte[MIDDLE]) {
			if (MIDDLE < 1)
				return MIDDLE;
			if (Math.abs(Math.abs(gesucht) - Math.abs(werte[MIDDLE])) < Math
					.abs(Math.abs(gesucht) - Math.abs(werte[MIDDLE - 1])))
				return MIDDLE;
			else
				return MIDDLE - 1;
		} else {
			if (MIDDLE > werte.length)
				return MIDDLE;
			else {
				if (Math.abs(Math.abs(gesucht) - Math.abs(werte[MIDDLE])) < Math
						.abs(Math.abs(gesucht) - Math.abs(werte[MIDDLE + 1])))
					return MIDDLE;
				else
					return MIDDLE + 1;
			}
		}
	}
}
