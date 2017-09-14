package Shellsort;

import java.util.Arrays;

public class Shellsort {
	static void shell_sort(int[] field){
		int iDist = 1;
		
		for(; iDist>0;iDist=iDist/3){
			for(int i1= iDist;i1<field.length;++i1){
				final int IVAL = field[i1];
				
				//System.out.println("Kopiere ["+field[i1] + "] in IVAL");
				
				int i2=i1;
				
				//System.out.println("Checken, ob ["+field[i2-iDist]+ "] mit dem Index "+(i2-iDist)+" größer ist als ["+IVAL+"]");
				
				while(i2>=iDist && field[i2-iDist]>IVAL){
					//System.out.println("In der While-Schleife checken, ob ["+field[i2-iDist]+"] mit dem Index "+(i2-iDist)+" größer ist als ["+IVAL+"]");
					
					field[i2]=field[i2-iDist];
					
					//System.out.println("["+field[i2-iDist]+ "] wechselte zum Index "+i2);
					
					i2=i2-iDist;
				}
				field[i2]=IVAL;
				
				//System.out.println("["+IVAL+"] wechselte zum Index "+i2);
				
				print(field);
			}
		}
	}
	
	static int[] generateRandomNumbers(){
		int[] a = new int[10];
		for(int i = 0; i<a.length;i++){
			a[i] = (int)(Math.random()*100%20);
		}
		return a;
	}
	
	static void print(int[] field){
		for(int i = 0; i<field.length;i++){
			System.out.print("["+field[i]+"]\t");					
		}
		System.out.println();
	}
	
	public static void main(String args[]){
		int[] a = {5,8,10,2,9,7,4,1,3,6};
		System.out.println("Vor dem sortieren:");
		print(a);
		System.out.println();
		System.out.println("Nach dem Sortieren:");
		shell_sort(a);
		
	}
}
