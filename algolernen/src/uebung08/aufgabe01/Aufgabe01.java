package uebung08.aufgabe01;

import java.io.IOException;

import uebung08.aufgabe01.UniqueNumberOperator.mode;

// Aufgabe 1: 
// Implementieren Sie einen Rot-Schwarz Baum ohne Verwendung der 
// NodeHandler Klasse und ohne R�ckw�rtsverkettung der Knoten zu ihren 
// V�terknoten. Testen Sie Ihre Implementierung, indem Sie sehr viele Eintr�ge 
// einf�gen und den Baum an Ende ausdrucken, so dass die Baumstruktur zu 
// erkennen ist. 

public class Aufgabe01 {

	int anzahlElemente = 20;

	public Aufgabe01() throws IOException, InterruptedException {
		RedBlackTree_noNH_wM<Integer, String> redBlackTree = new RedBlackTree_noNH_wM<Integer, String>();

		UniqueNumberOperator intGen = new UniqueNumberOperator(anzahlElemente,mode.writeFile);

		for (int i = 0; i < anzahlElemente; i++) {
			redBlackTree.insert(intGen.getUniqueInt(), "" + i);
		}
		redBlackTree.printBinaryTree();
	}

	public static void main(String[] args) throws IOException,
			InterruptedException {
		new Aufgabe01();
	}
}
