package uebung12.aufgabe02;

import java.io.IOException;

// Aufgabe 2: 
// Implementieren Sie zu einem ungewichteten Graphen auf Basis eine 
// Adjazenzlistenimplementierung(!!!!) die Berechnung der maximalen 
// Zusammenhangskomponenten. Zeichnen Sie den Graphen und färben Sie die 
// unterschiedlichen Zusammenhangskomponenten unterschiedlich farbig ein.

public class Aufgabe02 {
	final static int anzahlElemente = 10;

	Aufgabe02() throws IOException {
		GraphList gl = new GraphList(false);
		gl.newNode("Hamburg");
		gl.newNode("Bremen");
		gl.newNode("Bremerhaven");
		gl.newNode("Lemgo");
		gl.newNode("Aurich");
		gl.newNode("Westerland");
		System.out.println(gl.mRootNames.get("Hamburg").mIdent + " --> Hamburg");
		System.out.println(gl.mRootNames.get("Bremen").mIdent + " --> Bremen");
		System.out.println(gl.mRootNames.get("Bremerhaven").mIdent + " --> Bremerhaven");
		System.out.println(gl.mRootNames.get("Lemgo").mIdent + " --> Lemgo");
		System.out.println(gl.mRootNames.get("Aurich").mIdent + " --> Aurich");
		System.out.println(gl.mRootNames.get("Westerland").mIdent + " --> Westerland");
		
		gl.addEdge("Hamburg", "Bremen", 2);
		gl.addEdge("Hamburg", "Lemgo", 4);
		gl.addEdge("Hamburg", "Bremerhaven", 3);
		gl.addEdge("Bremerhaven", "Bremen", 1);
		gl.addEdge("Bremerhaven", "Lemgo", 7);
		gl.addEdge("Lemgo", "Aurich", 10);
		gl.addEdge("Lemgo", "Hamburg", 13);
		gl.addEdge("Aurich", "Bremen", 4);
		gl.addEdge("Westerland", "Hamburg", 4);
		gl.addEdge("Westerland", "Aurich", 17);
		
		Graph_Printer printer = new Graph_Printer(false, true);
//
//		for(int i = 0; i < anzahlElemente-1; ++i)
//			gl.newNode(getSpeakableString(5));
//
//		for(int i = 0; i < 2 * anzahlElemente-1; ++i) {
//			String start = gl.mRevRootNames.get(gl.mRoots.get(i % anzahlElemente));
//			String end = gl.mRevRootNames.get(gl.mRoots.get(((int) (Math.random() * anzahlElemente))));
//			int randWeight = (int) (Math.random() * 100);
//
//			gl.addEdge(start, end, randWeight);
//		}

		gl.print();
		
		gl.getMySpanBaumBitch("Lemgo");
		System.out.println("Maximale Zusammenhangskomponenten: " + gl.maxConnectedComponent());
//		float start = System.currentTimeMillis();
		printer.print("Graph", gl);
//		System.out.println("das hat " + (System.currentTimeMillis() - start) + " ms gedauert");

	}

	public static void main(String[] args) throws IOException {
		new Aufgabe02();
	}

	public static String getString(int len) {
		java.util.Random rnd = new java.util.Random();
		String s = new String();
		for(int j = 0; j < len; ++j)
			s += (char) (rnd.nextInt(26) + 65);
		return s;
	}

	public static String getSpeakableString(int len) {
		String s = new String();
		for(int j = 0; j < len; ++j)
			s += (j % 2 == 0 ? getVocale() : getconsonant());
		return s;
	}

	private static char getVocale() {
		java.util.Random rnd = new java.util.Random();
		char[] v = { 'A', 'E', 'I', 'O', 'U' };
		return v[rnd.nextInt(v.length - 1)];
	}

	private static char getconsonant() {
		java.util.Random rnd = new java.util.Random();
		char[] v = { 'B', 'C', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'V', 'W', 'X', 'Y', 'Z' };
		return v[rnd.nextInt(v.length - 1)];
	}

}
