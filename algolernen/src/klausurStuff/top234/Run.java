package klausurStuff.top234;

import java.io.IOException;

import klausurStuff.top234.UniqueNumberOperator.mode;

// Aufgabe 2: 
// Implementieren Sie für den Top-Down2-3-4-Baum eine Ausgabemethode für 
// uDrawGraph zur Visualisierung des Baums. 

public class Run {

	static final int anzahlElemente=50;
	
	public static void main(String[] args) throws IOException {
		Top234Tree tree = new Top234Tree();
		TreePrinter printer = new TreePrinter(false,true);
//		UniqueNumberOperator genRand = new UniqueNumberOperator(anzahlElemente,
//				mode.readFile);
//		for (int i = 0; i < anzahlElemente; ++i)
//			tree.insert(genRand.getUniqueInt());
		
		int[] a = {5,21,34,37,14,27,18,2,0,-8,17,6,66};
		for (int i = 0; i < a.length; ++i)
			tree.insert(a[i]);

//		tree.displayTree(); // consolenTreePrint
		printer.setTree(tree);
		printer.printTree("Top234");
	}
}
