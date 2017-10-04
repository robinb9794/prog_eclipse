package Zusatzaufgaben2014;

public class Aufgabe5 {
	public static void main(String args[]){
		Fahrzeug[] a = {new Auto("rot"), new Auto("blau"), new Flugzeug("weiﬂ")};
		for(Fahrzeug x : a){
			System.out.println("Bin "+x+" fahre "+x.geschwindigkeit()+" km/h und meine Farbe ist "+x.farbe()+".");
		}
		System.out.println("Es wurde insgesamt "+Fahrzeug.getAnzahlFahrzeuge()+" Objekte erstellt, die die Klasse Fahrzeug als Basisklasse verwenden.");
	}
}

class Fahrzeug{
	String farbe;
	int speed;
	static int counter;
	
	public String farbe(){
		return this.farbe;
	}

	public static int getAnzahlFahrzeuge(){
		return counter;
	}
	
	public int geschwindigkeit(){
		return this.speed;
	}
}

class Auto extends Fahrzeug{
	Auto(String farbe){
		this.farbe=farbe;
		this.speed=180;
		++counter;
	}
}

class Flugzeug extends Fahrzeug{
	Flugzeug(String farbe){
		this.farbe=farbe;
		this.speed=800;
		++counter;
	}
}
