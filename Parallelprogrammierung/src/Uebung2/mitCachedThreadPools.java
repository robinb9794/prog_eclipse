package Uebung2;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class mitCachedThreadPools implements Callable<Integer>{

	@Override
	public Integer call() throws Exception {
		for(int i = 0; i<=100; i+=20){
			System.out.println(Thread.currentThread().getName()+ " taskNumber: "+taskNumber+", percent complete: "+i);
			try{
				Thread.sleep((int)(Math.random()*1000));
			}catch(Exception e){
				
			}
		}
		return taskNumber;
	}
	
	private int taskNumber;
	
	mitCachedThreadPools(int taskNumber){
		this.taskNumber=taskNumber;
	}
	
	public static void main(String args[]){
		int numOfCallableTasks=4;
		
		ExecutorService es = Executors.newCachedThreadPool();
		mitCachedThreadPools callableTasks[] = new mitCachedThreadPools[numOfCallableTasks];
		Future<?> futures[] = new Future[numOfCallableTasks];
		
		for(int i = 0; i<numOfCallableTasks;i++){
			callableTasks[i]=new mitCachedThreadPools(i);
			futures[i]=es.submit(callableTasks[i]);
		}
		
		for(int i=0; i<numOfCallableTasks;i++){
			try{
				System.out.println("Ending task: "+futures[i].get());
			}catch(Exception e){
				
			}
		}
	}
	
}


