package Probeklausur_SOSE16_Versuch1;

public class Aufgabe2 {
	public static void main(String args[]){
		int n = 9;
		countBits(n);
	}
	
	static void countBits(int n){
		int xor=1;
		int count0=0;
		int count1=0;
		
		for(int i = 0; i<32-Integer.numberOfLeadingZeros(n);i++){
			if((n^xor)<n){
				++count1;
			}else{
				++count0;
			}
			xor = xor<<1;
		}
		System.out.print("Anzahl Einsen: "+count1+"\nAnzahl Nullen: "+count0);
	}
}
