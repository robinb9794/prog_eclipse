package Sortieren;

public class Bubble {
	public static void main(String arg[]){
		int[] a = {4,7,1,6,2,55,7,1,9};
		int[] sorted = bubblesort(a);
		
		for(int i=0; i<sorted.length;i++){
			System.out.print(sorted[i]+"\t");
		}
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
