package klausurStuff.klausurBestehenWarscheinlichkeit;

import java.util.Vector;

public class KlausurCalculator {
	private final int anzahlKlausurfragen;
	private final String[] klausurThemen;
	private final int[] kannIch;
	
	public int anzahlBestanden;
	public int anzahlDurchgefallen;
	public int anzahlMoeglicherPaarungen;
	public final Vector<String> moeglichePruefungen = new Vector<String>();
	public final Vector<String> bestandenePruefungen = new Vector<String>();
	public final Vector<String> nichtBestandenePruefungen = new Vector<String>();

	KlausurCalculator(int anzahlKlausurfragen, String[] klausurThemen,
			int[] kannIch) {
		this.anzahlKlausurfragen = anzahlKlausurfragen;
		this.klausurThemen = klausurThemen;
		this.kannIch = kannIch;
		doCalc();
	}

	public void doCalc() {
		doCalc(0, anzahlKlausurfragen, "", 0);
		anzahlBestanden=bestandenePruefungen.size();
		anzahlDurchgefallen=nichtBestandenePruefungen.size();
		anzahlMoeglicherPaarungen=moeglichePruefungen.size();
	}

	private void doCalc(int anfang, int count, String string, int bestanden) {
		if (count > 0) {
			for (int i = anfang; i < klausurThemen.length; ++i) {
				doCalc(anfang += 1, count - 1, string + "|" + klausurThemen[i],
						bestanden + kannIch[i]);
			}
		} else {
			moeglichePruefungen.add(string);
			if (bestanden >= (Math.rint(anzahlKlausurfragen/2.0)))
				bestandenePruefungen.add(string);
			else
				nichtBestandenePruefungen.add(string);
		}
	}
}
