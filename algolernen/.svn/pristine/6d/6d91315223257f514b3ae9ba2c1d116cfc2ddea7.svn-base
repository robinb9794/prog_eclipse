package klausurStuff.roBDD;

import java.io.IOException;
import klausurStuff.roBDD.RoBDD.Func;

// Aufgabe 2: 
// Implementieren Sie für RoBDDs eine Ausgabemethode für uDrawGraph zur 
// Visualisierung. 

public class Run {

	Run() throws IOException {
		RoBDD roBDD = new RoBDD();
		Func f;

		// zum testen...
		f = new FuncGen().genFunc(10, roBDD);

//		Func x = roBDD.genVar("x");
//		Func y = roBDD.genVar("y");
//		Func z = roBDD.genVar("z");
//		f = roBDD.and(roBDD.or(x, y), roBDD.or(roBDD.not(z), roBDD.not(y)));

		// der printeraufruf
		RoBDD_Printer printer = new RoBDD_Printer(false, true);
		String info = "MinDepth: " + roBDD.minDepth(f) + " | " + "MaxDepth: "
				+ roBDD.maxDepth(f) + " | " + "isSat: " + roBDD.isSat(f)
				+ " | " + "countNodes: " + roBDD.countNodes(f);
		System.out.println("Anzahl Knoten: " + printer.print("jo", f, info));

		// methoden
		System.out.println("MinDepth: " + roBDD.minDepth(f));
		System.out.println("MaxDepth: " + roBDD.maxDepth(f));
		System.out.println("isSat: " + roBDD.isSat(f));
		{
//			// fehlerhafte überlegung
//			 System.out.println("from x to y: "+roBDD.distanceAB(x, y));
//			 System.out.println("from x to z: "+roBDD.distanceAB(x, z));
//			 System.out.println("from y to z: "+roBDD.distanceAB(y, z)); //
//			 System.out.println("from z to x: "+roBDD.distanceAB(z, x));
		}

		try {
			Thread.sleep(25000);
		} catch (InterruptedException e) {
		}
	}

	public static void main(String[] args) throws IOException {
		new Run();
	}
}
