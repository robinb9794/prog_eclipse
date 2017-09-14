//initialisieren SIe ein zweidimensionales Array mit zuf�lligen Werten.
//Geben Sie die Zeile aus, die die gr��te Summe hat

package Uebungen;

public class RandomArray {
	
	public static int array[][] = new int[5][5];
	public static int r;
	public static int summe;
	public static int max=0;
	public static int merken;
	
	public static void main(String args[]){
	
	
	for(int i = 0; i<array.length; i++){
		summe = 0;
		for(int j = 0; j<array[i].length;j++){
			r = (int) (Math.random()*20);
			array[i][j]=r;
			
			if(j==0){
				System.out.print("Zeile "+(i+1)+": \t");
			}
			
			System.out.print(r+"\t");
			summe = summe+r;
			
			if(summe>max){
				max=summe;
				merken = i;
			}
		}
		System.out.println();
	}
	System.out.println();
	System.out.println("Gr��te Summe einer Zeile: "+max);
	System.out.println("Zeile: "+(merken+1));
	
	
	
	}
}
