package Probeklausur;

public class Aufgabe4{
	public static void main(String args[]){
		int[] a = {7,2,5,0,1,3,8,4,9,6};
		partialSort(a);
	}
	
	static void partialSort(int[] a){
		int temp;
		for(int i = 0; i<a.length/2;i++){
			for(int j = 0; j<(a.length+1)/2;j++){
				if(a[j]>a[j+1]){
					temp=a[j];
					a[j]=a[j+1];
					a[j+1]=temp;
				}
			}
			for(int k = 0; k<a.length;k++){
				System.out.print(a[k]+" ");
			}
			System.out.println();
		}
	}
}