package Verstaendnis_Threads;

public class Main {
	public static void main(String args[]){
		CustomThread t1 = new CustomThread("Fred 1",0);
		CustomThread t2 = new CustomThread("Fred 2", 0);
		
		t1.start();
		t2.start();
		
		//so lange warten, bis t1 und t2 fertig sind
		try{
			t1.join();
			t2.join();
		}catch(Exception e){
			
		}
		
		System.out.println("Ende des Programms");
	}
}
