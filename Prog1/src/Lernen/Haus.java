package Lernen;
/*
 * erst die Klassenattribute festlegen (Eigenschaften)
 */
public class Haus {
	
	int breite = 25;
	int hoehe = 3;
	int tiefe = 10;
	String farbe = "Rot";
	
	public Haus(){
		int breite = 15;
		this.breite = 20;	//this = breite von DIESER Klasse
	}

}
