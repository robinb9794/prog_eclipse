package Uebung2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class mitFixedThreadPools extends Thread{
String action;
	
	mitFixedThreadPools(String action){
		this.action=action;
	}
	
	public static void main(String args[]){
		Thread t1 = new Thread(new mitFixedThreadPools("Normal"));
		Thread t2 = new Thread(new mitFixedThreadPools("Fixed Thread Pools"));
		
		t1.start();
		try {
			t1.join();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t2.start();
		try {
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void run(){
		if(action=="Normal"){
			normal();
		}else if(action=="Fixed Thread Pools"){
			ftp();
		}
	}
	
	public static void normal(){
		System.out.println("\nNormal\n----------");
		
		for(int i =0; i<10;i++){
			System.out.println("Einzahlung");
			System.out.println("Auszahlung");
		}

	}
	
	public static void ftp(){
		System.out.println("\nMit Fixed Thread Pools\n----------");
		
		ExecutorService ex = Executors.newFixedThreadPool(5);
		for(int i = 0; i<10; i++){
			ex.execute(new Runnable(){
				public void run(){
					try{
						System.out.println("Einzahlung");
						Thread.sleep(1000);
						System.out.println("Auszahlung");
						Thread.sleep(1000);
					}catch(Exception e){
						
					}
				}
			});
		}
	}
}
