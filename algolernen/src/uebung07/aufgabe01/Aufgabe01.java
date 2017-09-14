package uebung07.aufgabe01;

import java.io.IOException;

import uebung07.aufgabe01.UniqueNumberOperator.mode;

// Aufgabe 1: 
// Implementieren Sie den Top-Down 2-3-4-Baum (mit Optimierung, sprich 4er 
// Knoten werden beim Abstieg zuerst aufgetrennt). Verwenden Sie dazu eine 
// Knotenklasse, die sich bis zu drei Schlüssel und bis zu vier Nachfahren merken 
// kann. Wenden Sie Ihre Implementierung auf die folgende Reihenfolge von Schlüsseln 
// an und lassen Sie sich die entstehende Baumstruktur ausgeben. 
// 13 –7 34 3 5 12 9 -200 45 14 -1 15 -6 18 -9 44 

public class Aufgabe01 {

	final static int anzahlElemente = 20;
	
	public static void main(String[] args) throws IOException {
		Top234Tree tree = new Top234Tree();
		UniqueNumberOperator genRand = new UniqueNumberOperator(anzahlElemente,mode.readFile);
		
		for(int i=0;i<anzahlElemente;++i)
			tree.insert(genRand.getUniqueInt());
		
		tree.displayTree();
	}
}
