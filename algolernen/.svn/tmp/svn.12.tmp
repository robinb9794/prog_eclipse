package uebung10.aufgabe02;

import java.io.IOException;

// Aufgabe 2: 
// Implementieren Sie für Patricia Trees eine Ausgabemethode für uDrawGraph 
// zur Visualisierung. 

public class Aufgabe02 {
	Aufgabe02() throws IOException{
		PatriciaTreeWithNH<Integer> patriciaTree = new PatriciaTreeWithNH<Integer>();
		SpeakableString s = new SpeakableString(15);
		
		for(int i=0;i<10;++i){
			patriciaTree.insert(s.getNext(), i);
		}

		TreePrinter treePrinter = new TreePrinter(false, true);
		treePrinter.setTree(patriciaTree);
		treePrinter.printTree("Patricia-Tree");
	}
	
	public static void main(String[] args) throws IOException {
		new Aufgabe02();
	}
}
