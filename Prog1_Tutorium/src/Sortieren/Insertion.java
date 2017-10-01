package Sortieren;

/**
 * es wird nicht nach dem kleinsten Element gesucht, sondern 
 * der Algorithmus geht nach und nach die Elemente des Arrays 
 * durch und fügt diese im linken, sortierten Teil des Arrays
 *	an passender Stelle ein 
 * @author Robin
 *
 */
public class Insertion {
	public static void main(String args[]){
		int[] a = {4,7,1,6,2,55,7,1,9};
		int[] sorted = insertionsort(a);
		
		for(int i=0; i<sorted.length;i++){
			System.out.print(sorted[i]+"\t");
		}
	}

	private static int[] insertionsort(int[] a) {
		int m,j;
		for(int i=1; i<a.length;i++){
			j=i;
			m=a[i];
			while(j>0 && a[j-1]>m){
				a[j]=a[j-1];
				j--;
			}
			a[j]=m;
		}
		return a;
	}
}
