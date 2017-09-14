package Uebungen;

public class For1x1 {
	public static void main(String args[]){
		int erg = 1;
		int[][] x = new int[11][11];
		for(int i = 1; i<x.length;i++){
			for(int j = 1; j<x[i].length;j++){
				erg=i*j;
				System.out.println(i+"*"+j+"="+erg);
			}
		}
	}
}
