package Uebungen;

public class Array1x1 {
	public static void main(String args[]){
		
		int x = 0;
		int y = 1;
		int array[][] = new int[10][10];
		
		for(int i = 0; i<array.length;i++){
			for(int j = 0; j<array[i].length;j++){
				if(i==0){
					System.out.print(x+"\t");
					++x;
				}else if(j==0){
					System.out.print(y+"\t");
					++y;
				}else if((i!=0)&&(j!=0)){
					System.out.print((i*j)+"\t");
				}
			}
			System.out.println();
		}
	}

}
