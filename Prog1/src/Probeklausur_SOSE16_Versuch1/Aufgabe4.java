package Probeklausur_SOSE16_Versuch1;

public class Aufgabe4 {
	public static void main(String args[]){
		System.out.println(isLess(20,19));
	}
	
	static boolean isLess(int i1, int i2){
		int xor=i1^i2;
		i1 &= xor;
		i2 &= xor;
		while(i2!=0){
			if(i1==0){
				return true;
			}
			i1>>=1;
			i2>>=1;
		}
		return false;
	}
}
