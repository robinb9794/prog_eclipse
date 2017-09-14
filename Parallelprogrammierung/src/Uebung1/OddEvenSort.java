package Uebung1;

public class OddEvenSort {
	public static void fill(int[] array){
		for(int i = 0; i<array.length;i++){
			array[i] = (int)((Math.random()*20)+1);
		}
	}
	
	public static void print(int[] array){
		for(int i = 0; i<array.length;i++){
			System.out.print(array[i]+"\t");
		}
		System.out.println();
	}
	
	public static void main(String args[]){
		int[] array = new int[9];
		fill(array);
		sort(array);
	}
	
	public static void sort(int[] array){
		for(int i=0; i<array.length;i++){
			System.out.print((i+1)+") ");
			for(int j = 0; j<array.length;j++){
				if(i%2==0){
					try{
						if(array[j]>array[j+1]){
							int tmp=array[j];
							array[j]=array[j+1];
							array[j+1]=tmp;
						}
					}catch(Exception e){
						
					}
				}else{
					try{
						if(array[j]<array[j-1]){
							int tmp=array[j];
							array[j]=array[j-1];
							array[j-1]=tmp;
						}
					}catch(Exception e){
						
					}
				}
			}
			print(array);
		}
	}
}
