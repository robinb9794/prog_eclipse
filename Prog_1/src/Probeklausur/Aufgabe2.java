package Probeklausur;

public class Aufgabe2 {
	public static void main(String args[]){
		int[] a = {1,2,3,4};
		rec(a,0,false);
	}
	
	public static void rec(int[] a, int i, boolean b){
		if(i<a.length){
			if(!b){
				System.out.print(a[i]+" ");
				rec(a,i+1,b);
			}else{
				rec(a,i+1,b);
				System.out.print(a[i]+" ");
			}
			
		}
	}
}	
