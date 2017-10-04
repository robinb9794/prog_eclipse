package uebung08.aufgabe02;

import java.io.IOException;
import uebung08.aufgabe02.UniqueNumberOperator.mode;

// Aufgabe 2: 
// Implementieren Sie für den Rot-Schwarz Baum eine Ausgabemethode für 
// uDrawGraph (siehe Aufgabe Übung 8)zur Visualisierung des Baums.

public class Aufgabe02 {

	final int anzahlElemente = 50;

	Aufgabe02() throws IOException {
		RedBlackTree_noNH_wM<Integer, String> redBlackTree_noNH_wM = new RedBlackTree_noNH_wM<Integer, String>();
		UniqueNumberOperator intGen = new UniqueNumberOperator(anzahlElemente,mode.readFile);

		for(int i=0;i<anzahlElemente;++i){
			int tmp=intGen.getUniqueInt();
			redBlackTree_noNH_wM.insert(tmp,tmp+"");
		}

		TreePrinter<Integer,String> treePrinter = new TreePrinter<Integer, String>(false,true);
		treePrinter.setTree(redBlackTree_noNH_wM);
		treePrinter.printTree("ohne NodeHandler mit Methoden");
	}

	public static void main(String[] args) throws IOException {
		new Aufgabe02();
	}
}
