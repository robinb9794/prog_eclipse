package Lernen;

public class Exception {
	public static void main(String args[]){
		int[] a = {};
		test(a);
	}
	
	static void test(int[] a){
		try{
			System.out.println(a[0]);
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println("Das Array ist leer.");
		}
	}
}
