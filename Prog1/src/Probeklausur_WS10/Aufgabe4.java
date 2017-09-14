package Probeklausur_WS10;

public class Aufgabe4 {
	public static void main(String args[]){
		int[] a = {};
		System.out.println(rec(a,0,0));
	}
	
	static int rec(int[] a, int i, int max){
		if(i<a.length){
			try{
				return a[i]>max ? rec(a,i+1,a[i]) : rec(a,i+1,max);
			}catch(ArrayIndexOutOfBoundsException e){
				System.out.println("Fehler.");
			}
		}
		return max;		
	}
}
