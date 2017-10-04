package Probeklausur;

public class Aufgabe1 {
	static void test(int[] a, int x){
		int i= 0;
		try{
			while(i<x){
				System.out.println(a[i++]);
			}
		}catch(ArrayIndexOutOfBoundsException ex){
			test(a,i,x);
		}
	}
	
	static void test(int[] a, int i, int x){
		System.out.println(x);
		test(a,i-1);
	}
	
	public static void main(String args[]){
		int[] a = {1,2,3,4};
		test(a,6);
	}
}
