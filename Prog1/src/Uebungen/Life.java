package Uebungen;

public class Life {
	
	int zeilen = 5;
	int spalten = 11;
	int mod;
	int zaehler;
	int round=1;                                                                                     
	double random;
	int[][] a = new int[zeilen][spalten];	
	
	public Life(){
		this.random = random;		
		for(int i = 0; i<a.length;i++){
			for(int j = 0; j<a[i].length;j++){
				random = Math.random();
				if(random>0.5){
					a[i][j]=1;
				}else{
					a[i][j]=0;
				}
			}
			++mod;
		}
	}
	
	public void ausgabe(){
		this.round=round;
		System.out.println("Spielbrett: "+round);
		
		for(int i = 0; i<a.length;i++){
			for(int j = 0; j<a[i].length;j++){
				if(a[i][j]==0)
					System.out.print(" ");
				if(a[i][j]==1)
					System.out.print("a");
				if(a[i][j]==2)
					System.out.print("b");
				if(a[i][j]==3)
					System.out.print("c");
				if(a[i][j]==4)
					System.out.print("d");
				if(a[i][j]==5)
					System.out.print("e");
				if(a[i][j]>5)
					System.out.print("f");
			}
			System.out.println();
		}
		++round;
	}
	
	public void empty(){
		for(int i = 0; i<3;i++){
			System.out.println();
		}
	}
	
	public void waitOneSecond(){
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void calculateNew(){		
		this.zaehler=zaehler;
		int[][] c = new int[zeilen][spalten];
		
		for(int iZ = 0; iZ < a.length; ++iZ) {
			for(int iS = 0; iS < a[iZ].length; ++iS) {
				zaehler=0;
				if(a[(iZ-1+a.length)%(a.length)][iS] >0){
	 				++zaehler;
	 			}
	 			if(a[iZ][(iS-1+a[iZ].length)%(a[iZ].length)] >0){	//(i-1+a.lenght)%(a.length)
	 				++zaehler;																					
	 			}
	 			if(a[(iZ+1)%a.length][(iS-1+a[iZ].length)%(a[iZ].length)] >0){
	 				++zaehler;
		 			}
	 			if(a[(iZ-1+a.length)%(a.length)][(iS+1)%a[iZ].length] >0){
	 				++zaehler;
	 			}
	 			if(a[(iZ+1)%a.length][iS] >0){
	 				++zaehler;
	 			}
	 			if(a[iZ][(iS+1)%a[iZ].length] >0){ //a[i][(j+1)%a[i].length]
	 				++zaehler;
	 			}
	 			if(a[(iZ+1)%a.length][(iS+1)%a[iZ].length] >0){
	 				++zaehler;
	 			}
	 			if(a[(iZ-1+a.length)%(a.length)][(iS-1+a[iZ].length)%(a[iZ].length)] >0){
	 				++zaehler;
	 			}

				if(a[iZ][iS] >0 && zaehler == 3) {
					++a[iZ][iS];
				}
				else if(a[iZ][iS] >0 && zaehler == 2) {
					++a[iZ][iS];
				}
				else if(a[iZ][iS] ==0 && zaehler == 3) {
					++a[iZ][iS];
				}
				else if(zaehler > 3 || zaehler < 2) {
					a[iZ][iS]=0;
				}
				else{
					a[iZ][iS]=0;
				}
			}
		}
	}
}
