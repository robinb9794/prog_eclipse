package Sortieren;

/**
 * - Array teilen in sortiert und unsortiert
 * - nach dem kleinsten Element suchen
 * - mit dem ersten Element tauschen
 * @author Robin
 *
 */
public class Selection {
	public static void main(String args[]){
		int[] a = {4,7,1,6,2,55,7,1,9};
		int[] sorted = selectionsort(a);
		
		for(int i=0; i<sorted.length;i++){
			System.out.print(sorted[i]+"\t");
		}
	}

	private static int[] selectionsort(int[] a) {
		int temp;
		for(int i=0; i<a.length-1;i++){
			for(int j=i+1; j<a.length;j++){
				if(a[i]>a[j]){
					temp=a[i];
					a[i]=a[j];
					a[j]=temp;
				}
			}
		}
		return a;
	}
}
