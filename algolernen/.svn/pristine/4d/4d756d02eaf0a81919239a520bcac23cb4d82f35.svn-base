package uebung08.aufgabe03;

import java.io.IOException;

import uebung08.aufgabe03.UniqueNumberOperator.mode;

// Aufgabe 3: 
// Implementieren Sie für den Rot-Schwarz Baum eine Methode, die die Tiefe des 
// Baums ermittelt. Dabei sollen aber 3er und 4er Knoten zusammengefasst 
// werden, d.h. rote Kanten werden nicht gezählt. Der nachfolgende Baum hätte 
// damit eine Tiefe von 3. 

public class Aufgabe03 {

	static int anzahlElemente = 1500;
	
	public Aufgabe03() throws IOException, InterruptedException {
		RedBlackTree_noNH_wM<Integer, String> redBlackTree_noNH_wM = new RedBlackTree_noNH_wM<Integer, String>();

		UniqueNumberOperator intGen = new UniqueNumberOperator(anzahlElemente,mode.writeFile);
		
		for(int i = 0;i<anzahlElemente;++i)
			redBlackTree_noNH_wM.insert(intGen.getUniqueInt(), "testInsert"+i);
		
		System.out.println("leafCount: "+redBlackTree_noNH_wM.countLeaf());
		System.out.println("Tiefe des Baums: "+redBlackTree_noNH_wM.treeDept());
		
	}

	public static void main(String[] args) throws IOException,
			InterruptedException {
		new Aufgabe03();
	}
}
