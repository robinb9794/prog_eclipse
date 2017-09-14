package Verstaendnis_Threads;

public class CustomThread extends Thread{
	String name;
	Integer counter;
	
	CustomThread(String name, Integer counter){
		this.name=name;
		this.counter=counter;
	}
	
	public void run(){
		System.out.println("Starte Thread: "+name);
		
		synchronized(counter){
			for(int i = 0; i<5;i++){
				try{
					Thread.sleep(100);
					System.out.println(counter++ + "("+name+")");
				}catch(Exception e){
					
				}
			}
		}		
		
		System.out.println("Beende Thread: "+name);
	}
}
