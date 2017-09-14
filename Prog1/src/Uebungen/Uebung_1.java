package Uebungen;

public class Uebung_1 {
	public static void main(String args[]){
		
		int n = 10;
		int[] field = new int[n];
		int produkt = 1;
		
		for(int i = 1; i<=n; i++){
			System.out.print(produkt + " * " + i + " = ");
			produkt = produkt*i;
			System.out.print(produkt);
			System.out.println();
		}
		
		
		
	}

}