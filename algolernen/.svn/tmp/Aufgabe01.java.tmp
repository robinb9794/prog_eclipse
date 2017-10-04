package uebung10.aufgabe01;

import java.io.IOException;

// Aufgabe 1: 
// Implementieren Sie Patrica Trees ohne Verwendung der NodeHandler
// Klasse, in denen Sie zu Strings beliebige Werte (Generics verwenden) 
// abspeichern können. 

public class Aufgabe01 {
	Aufgabe01() throws IOException {
		TreePrinter printer = new TreePrinter(false, true);
		PatriciaTree_noNH_wM<Integer> patriciaTree_noNH_wM = new PatriciaTree_noNH_wM<Integer>();
		
		String[] M = {"hallo","test","noch n test","jojo","bla"};
		
		for(int i=0; i<M.length; ++i) {
			patriciaTree_noNH_wM.insert(M[i],i);
		}

		printer.setTree(patriciaTree_noNH_wM);
		printer.printTree("patriciaTree_noNH_wM");
	}

	public static void main(String[] args) throws IOException {
		new Aufgabe01();
	}
}
