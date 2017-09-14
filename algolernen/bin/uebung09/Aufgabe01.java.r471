package uebung09;

import java.io.IOException;

// Aufgabe 1: 
// Implementieren Sie für Ihren Rot-Schwarz Baum aus Übung 8 Aufgabe 1 die remove 
// Methode ohne Verwendung der NodeHandlerKlasse. 
// Wenden Sie Ihre Implementierung auf die folgende Reihenfolge von Schlüsseln 
// an und lassen Sie sich die entstehende Baumstruktur ausgeben. 
// 13 –7 34 3 5 12 9 -200 45 14 -1 15 -6 18 -9 44 
// Danach löschen Sie die Schlüssel 
// 13 -7 45 12 -1 
// und lassen sich nach jedem Löschen immer wieder die Baumstruktur ausgeben. 

public class Aufgabe01 {

	public Aufgabe01() throws IOException, InterruptedException {
		RedBlackTree<Integer, String> redBlackTree = new RedBlackTree<Integer, String>();
		TreePrinter<Integer, String> treePrinter = new TreePrinter<Integer, String>(
				false, true);
		
		int[] a = {13,-7,34,3,5,12,9,-200,45,14,-1,15,-6,18,-9,44};
		
		for (int i = 0; i < a.length; i++) {
			redBlackTree.insert(a[i],a[i]+"");
		}

		treePrinter.setTree(redBlackTree);
		treePrinter.printTree("vor remove");
		
		System.out.println("remove: " + redBlackTree.remove(13));
		treePrinter.setTree(redBlackTree);
		treePrinter.printTree("nach remove 13");
		
		System.out.println("remove: " + redBlackTree.remove(-7));
		treePrinter.setTree(redBlackTree);
		treePrinter.printTree("nach remove -7");
		
		System.out.println("remove: " + redBlackTree.remove(45));
		treePrinter.setTree(redBlackTree);
		treePrinter.printTree("nach remove 45");
		
		System.out.println("remove: " + redBlackTree.remove(12));
		treePrinter.setTree(redBlackTree);
		treePrinter.printTree("nach remove 12");
		
		System.out.println("remove: " + redBlackTree.remove(-1));
		treePrinter.setTree(redBlackTree);
		treePrinter.printTree("nach remove -1");
	}

	public static void main(String[] args) throws IOException,
			InterruptedException {
		new Aufgabe01();
	}
}
