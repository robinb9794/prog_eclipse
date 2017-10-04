package Probeklausur_WS15_Versuch1;

public class Aufgabe1 {
	public static void main(String args[]){
		int[] a = {5,4,6,-2,31,-7};
		System.out.print("Unsortiert: ");
		for(int i =0; i<a.length;i++){
			System.out.print(a[i]+" ");
		}
		System.out.println();
		sort(a);
	}
	
	static void sort(int[] a){
		int temp;
		for(int i = 1; i<a.length;i++){
			for(int j = 0;j<a.length-i;j++){
				if(a[j]>a[j+1]){
					temp=a[j];
					a[j]=a[j+1];
					a[j+1]=temp;
				}
			}
			for(int k = 0;k<a.length;k++){
				System.out.print(a[k]+" ");
			}
			System.out.println();
		}
	}
}