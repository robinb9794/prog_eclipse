package uebung12.aufgabe01;

import java.io.IOException;
import java.util.HashSet;

// Aufgabe 1: 
// Implementieren Sie zu einem gewichteten Graphen auf Basis eine 
// Adjazenzlistenimplementierung(!!!!) die Berechnung eines minimalen 
// Spannbaums. Verwenden Sie dazu eine Prioritätenheap. Zeigen Sie das Ergebnis 
// grafisch an. Testen (Laufzeitmessung) Sie Ihre Datenstruktur mit einem Graphen,
// der mindestens 1000 Knoten und 5000 Kanten hat. 

public class Aufgabe01 {
	final static int anzahlElemente = 5;

	Aufgabe01() throws IOException {
		GraphList gl = new GraphList(true);
		SpeakableString s = new SpeakableString(7);
		Graph_Printer printer = new Graph_Printer(false, true);
		
		for (int i = 0; i < anzahlElemente; ++i)
			gl.newNode(s.getNext());
		for (int i = 0; i < 2 * anzahlElemente - 1; ++i) {
			String start = gl.mRevRootNames.get(gl.mRoots.get(i
					% anzahlElemente));
			String end = gl.mRevRootNames.get(gl.mRoots.get(((int) (Math
					.random() * anzahlElemente))));
			int randWeight = (int) (Math.random() * 100);
			gl.addEdge(start, end, randWeight);
		}

		// uDrawGraph-Ausgabe
		double start = System.currentTimeMillis();
		printer.print("Graph", gl);
		System.out.println("das hat " + (System.currentTimeMillis() - start)
				+ " ms gedauert");
		
		HashSet<Node> cycle=gl.getCycle();
		System.out.println(cycle.size());
		for(Node node:cycle)
			System.out.println(gl.mRevRootNames.get(node));

		// //Consolen-print
		// gl.print();

//		String testKnoten = gl.mRevRootNames.get(gl.mRoots.get(0));
//		System.out.println("TestKnoten: " + testKnoten + "\n");
//		printer.print("MinSpanTree", gl.getMinSpanTree(testKnoten));
//
//		// // Console-TestKnoten-Spannbaum
//		 gl.getMySpanBaumBitch(testKnoten);
	}

	public static void main(String[] args) throws IOException {
		new Aufgabe01();
	}
}