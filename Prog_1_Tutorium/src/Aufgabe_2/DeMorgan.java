package Aufgabe_2;

public class DeMorgan {
	public static void main(String args[]){
		boolean a = false;
		boolean b = true;

		if ((!(a && b)) == (!a || !b)) {
			System.out.println("erste if-Anweisung.");
		}

		if ((!(a || b)) == (!a && !b)) {
			System.out.println("zweite if-Anweisung.");

		}
	}	
}
