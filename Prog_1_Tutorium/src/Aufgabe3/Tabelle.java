package Aufgabe3;

public class Tabelle {
	public static void main(String args[]){
		int i =0, j=0, n=10;
		while(i<n){
			while(j<n){
				if(i==0){
					System.out.print(j+"\t");
				}else if(j==0){
					System.out.print(i+"\t");
				}else{
					System.out.print(i*j+"\t");
				}
				++j;
			}
			System.out.println();
			j=0;
			++i;
		}
	}
}
