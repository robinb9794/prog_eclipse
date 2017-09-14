package Zusatzaufgaben2014;

public class Aufgabe2 {
	public static void main(String args[]){
		char[] c = {'L','A','G','E','R','R','E','G','A','L'};
		System.out.println(pali(c, 0, c.length-1, false));
	}
	
	static boolean pali(char[] c, int i, int j, boolean b){
		if(i<c.length){
			return c[i]==c[j] ? pali(c,i+1,j-1,true) : false;
		}
		return b;
	}
}
