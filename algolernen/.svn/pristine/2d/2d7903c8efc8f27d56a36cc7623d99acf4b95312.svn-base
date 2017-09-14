package uebung11.aufgabe02;

import java.io.IOException;
import uebung11.aufgabe02.RoBDD.Func;


// Aufgabe 2: 
// Implementieren Sie für RoBDDs eine Ausgabemethode für uDrawGraph zur 
// Visualisierung. 

public class Aufgabe02 {

	Aufgabe02() throws IOException{
		RoBDD roBDD = new RoBDD();
		
		// zum testen...
//		Func f = roBDD.and(roBDD.genVar("x"),(roBDD.genVar("y")));
		Func f = roBDD.and(roBDD.aequivalenz(roBDD.and(roBDD.genVar("x"), roBDD.genVar("y")),roBDD.genVar("z")),roBDD.aequivalenz(roBDD.and(roBDD.genVar("a"), roBDD.genVar("b")),roBDD.genVar("c")));
			
		// der printeraufruf mit zeitmessung
		RoBDD_Printer printer = new RoBDD_Printer(false, true);
		printer.print("jo",f);
	}
	
	public static void main(String[] args) throws IOException {
		new Aufgabe02();
	}
}
