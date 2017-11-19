package Aufgabe_5;

public class A5 {
	public static void main(String args[]) {
		int[][] a = { { 1, 2, 3 }, { 44, 55 }, { -10 } };
		serialize(a);
	}

	public static void serialize(int[][] a) {
		int counter = 0;
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				++counter;
			}
			++counter;
		}
		int[] b = new int[counter-1];
		counter=0;
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				b[counter++]=a[i][j];
			}
			++counter;
		}
		printArr(b);
	}
	
	public static void printArr(int[] b){
		for(int i=0; i<b.length;i++){
			System.out.print(b[i]+"\t");
		}
	}
}
