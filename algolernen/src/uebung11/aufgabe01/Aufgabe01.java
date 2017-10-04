package uebung11.aufgabe01;

import java.io.IOException;

import uebung11.aufgabe01.RoBDD.Func;

// Aufgabe 1: 
// Implementieren Sie die RoBDDs und f�gen Sie noch Funktionen f�r 
// Konjunktion, Disjunktion, Negation, Implikation und �quivalenz hinzu. 
// Variablen sollen nicht mehr durch Zahlen sondern durch Namen repr�sentiert 
// werden. Dazu sollten Sie eine weitere Hashtabelle einf�hren, die die Namen auf 
// die Variablenintegerwerte abbildet. 

public class Aufgabe01 {

	Aufgabe01() throws IOException {
		RoBDD roBDD = new RoBDD();
		Func x = roBDD.genFalse();
		Func y = roBDD.genFalse();
		
		// Test der boolschen Funktionen ------------
		for(int i=0;i<2;++i){
			if(x.isTrue())
				x = roBDD.genFalse();
			else
				x = roBDD.genTrue();
			for(int j=0;j<2;++j){
				if(y.isTrue())
					y = roBDD.genFalse();
				else
					y = roBDD.genTrue();
				
				System.out.println("x " + x.isTrue());
				System.out.println("y " + y.isTrue());
				System.out.print("[x&y = " + roBDD.and(x, y).isTrue()+"] "); // and
				System.out.print("|| [x|y = " + roBDD.or(x, y).isTrue()+"] "); // and
				System.out.print("|| [not x = " + roBDD.not(x).isTrue()+"] "); // not
				System.out.print("|| [not y = " + roBDD.not(y).isTrue()+"] "); // not
				System.out.print("|| [x->y = " + roBDD.implikation(x, y).isTrue()+"] "); // implikation
				System.out
						.println("|| [x<->y = " + roBDD.aequivalenz(x, y).isTrue() + "]\n"); // �quivalenz
				System.out.println("------------------------------------------------------\n");
				}
			}
	}

	public static void main(String[] args) throws IOException {
		new Aufgabe01();
	}
}
