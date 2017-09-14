package zusatz.approximation.approximate1D;

public class Run {

	public Run() {
		int[] werte = new int[999999];

		for (int i = 0; i < werte.length; ++i)
			werte[i] = (int) (Math.random() * 999999);

		Approximation1D a = new Approximation1D(werte);

		double start = System.currentTimeMillis();
		for (int i = 0; i < 2073600; ++i) {
			int gesucht = (int)(Math.random()*999);
//			System.out.println("gesucht: " + gesucht);

			int index = a.approximate1d(gesucht, 0, werte.length - 2);
//			System.out.println("approximierter wert: " + werte[index]
//					+ " an stelle: " + index);
		}
		
//		System.out.println(a.approximate1d(999998, 0, werte.length));
		
		System.out.println(System.currentTimeMillis()-start+" ms");
	}

	public static void main(String[] args) {
		new Run();
	}
}
