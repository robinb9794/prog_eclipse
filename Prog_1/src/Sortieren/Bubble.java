package Sortieren;

/**
 * - von links nach rechts durchlaufen
 * - das aktuelle Element mit dem Rechten vergleichen
 * - wenn das aktuelle Element größer ist, werden sie vertauscht
 * @author Robin
 *
 */
public class Bubble {
	public static void main(String arg[]){
		int[] a = {4,7,1,6,2,55,7,1,9};
		
		long start = System.currentTimeMillis();
		
		int[] sorted = bubblesort(a);
		
		long end = System.currentTimeMillis();
		
		for(int i=0; i<sorted.length;i++){
			System.out.print(sorted[i]+"\t");
		}
		System.out.println("\n"+(end-start)+"ms");
	}
	
	public static int[] bubblesort(int[] a){
		int temp;
		for(int i=1; i<a.length;i++){
			for(int j=0;j<a.length-1;j++){
				if(a[j]>a[j+1]){
					temp=a[j];
					a[j]=a[j+1];
					a[j+1]=temp;
				}
			}
		}
		return a;
	}
}
