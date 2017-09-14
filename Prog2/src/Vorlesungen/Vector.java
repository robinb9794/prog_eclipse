package Vorlesungen;
import java.lang.*;

public class Vector {
	int inkrementweite;
	int weiteresElement;
	Object[] objects;
	
	public static void makeALotInsertion(Vector vec){
		long lStart = System.currentTimeMillis();
		for(int i = 0; i<50000;++i){
			vec.add(new Integer(i));
		}
		long lEnd = System.currentTimeMillis();
		System.out.println("Zeit in mSec.:"+(lEnd-lStart));
	}
	
	public static void main(String args[]){
		Vector vec = new Vector();
		for(int i = -100; i<100;++i){
			vec.add(new Integer(i));
		}
		try{
			for(int i = 0; i<vec.size();i++){
				System.out.println(vec.get(i));
			}
		}catch (OutOfBoundsException e){
			System.out.println("Zu weit.");
		}
	}
	
	public Vector(){
		this(1,0);
	}
	
	public Vector(int vecKapazitaet){
		this(vecKapazitaet, 0);
	}
	
	public Vector(int vecKapazitaet, int vecVergroessern){
		inkrementweite = vecVergroessern;
		weiteresElement=0;
		objects = new Object[vecKapazitaet];
	}
	
	public void add(Object obj){
		//ist noch Platz für ein weiteres Element?
		if(weiteresElement>=objects.length){
			resize();
		}
		objects[weiteresElement++]=obj;
	}
	
	public void resize(){
		final int neueGroesse = inkrementweite == 0?
				objects.length*2 : objects.length+inkrementweite;
		Object[] neueObjects = new Object[neueGroesse];
		
		for(int i = 0; i<objects.length;++i){
			//kopiere die alten Objekte
			neueObjects[i]=objects[i];
		}
		objects=neueObjects;
	}
	
	public Object get(int index) throws OutOfBoundsException{
		if(index <size() && index>=0){
			return objects[index];
		}else{
			throw new OutOfBoundsException();
		}
	}
	
	public int size(){
		return weiteresElement;
	}
	
	public void set(int index, Object obj) throws OutOfBoundsException{
		if(index <size() && index>=0){
			objects[index]=obj;
		}else{
			throw new OutOfBoundsException();
		}
	}
}

class OutOfBoundsException extends Exception{}

