package klausurStuff.klausurBestehenWarscheinlichkeit;

public class Run {

	public static void main(String[] args) {

		// ------------------------------------------------------------------------------------
		String[] klausurThemen = { "RoBDD tiefe", "RoBDD knoten", "Patricia",
				"Graphen", "Huffman", "Bresenham", "Top-2-3-4 loeschen",
				"Rot-Schwarz count", "Rot schwarz löschen", "rb einfügen",
				"Matrizen" };
		int[] kannIch = { 1,1,1,0,1,0,1,1,1,1,0 };
		int anzahlKlausurfragen = 3;
		// ------------------------------------------------------------------------------------

		KlausurCalculator calc = new KlausurCalculator(anzahlKlausurfragen,
				klausurThemen, kannIch);

		System.out.println("Anzahl moeglicher Klausur-Fragen-Paarungen: "
				+ calc.anzahlMoeglicherPaarungen);
		System.out.println("Anzahl bestandener Pruefungen: "
				+ calc.anzahlBestanden);
		System.out.println("Anzahl nicht bestandener Pruefungen: "
				+ calc.anzahlDurchgefallen);
		System.out
				.println("Warscheinlichkeit zu bestehen: "
						+ (int) ((100.0 / calc.anzahlMoeglicherPaarungen) * calc.anzahlBestanden)
						+ "%");

//		System.out.println("\nMoegliche Pruefungen:");
//		for (String string : calc.moeglichePruefungen)
//			System.out.println(string);
//		
//		System.out.println("\nBestandene Pruefungen:");
//		for (String string : calc.bestandenePruefungen)
//			System.out.println(string);
//		
//		System.out.println("\nNicht-Bestandene Pruefungen:");
//		for (String string : calc.nichtBestandenePruefungen)
//			System.out.println(string);
	}
}
