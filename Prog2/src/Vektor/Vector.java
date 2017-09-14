package Vektor;

public class Vector<T> implements SimpleCollection<Integer> {
	int inkrementweite;
	int posWeiteresElement;
	Object[] objects;
	
	public Vector(){
		this(1,0);
	}
	
	public Vector(int vecKap){
		this(vecKap,0);
	}
	
	public Vector(int vecKap, int vecVergroessern){
		inkrementweite=vecVergroessern;
		posWeiteresElement=0;
		objects=new Object[vecKap];
	}
	
	public void resize(){
		final int neueGroesse;
		if(inkrementweite==0){
			neueGroesse=objects.length*2;
		}else{
			neueGroesse=objects.length+inkrementweite;
		}
		Object[] newObjects=new Object[neueGroesse];
		
		for(int i = 0; i<objects.length;i++){
			newObjects[i]=objects[i];
		}
		objects=newObjects;
	}
	
	public void add(T obj){
		if(posWeiteresElement>=objects.length){
			resize();
		}
		objects[posWeiteresElement++]=obj;
	}
	
	@Override
	public int size() {
		return posWeiteresElement;
	}

	@Override
	public void push_front(Integer arg) {
		if(posWeiteresElement>=objects.length){
			resize();
		}
		for(int i = size()-1;i>0;i--){
			objects[i]=objects[i+1];
		}
		objects[0]=arg;
		
	}

	@Override
	public void push_back(Integer arg) {
		if(posWeiteresElement>=objects.length){
			resize();
		}
		for(int i = 0; i<size();i++){
			objects[i]=objects[i+1];
		}
		objects[size()-1]=arg;
	}

	@Override
	public Integer get(int i) throws OutOfBoundsException {
		if(i>=0 && i<size()){
			return (int) objects[i];
		}else{
			throw new OutOfBoundsException();
		}
	}

	@Override
	public void set(int i, Integer arg) throws OutOfBoundsException {
		if(i>=0 && i<size()){
			objects[i]=arg;
		}else{
			throw new OutOfBoundsException();
		}
		
	}

	@Override
	public void delete(int start, int end) {
		if(end<start || end>posWeiteresElement){
			return;
		}else{
			int distance=end-start+1;
			for(int i = start; i<posWeiteresElement;i++){
				objects[i]=objects[i+distance];
			}
			posWeiteresElement=posWeiteresElement-distance;
		}
		
	}

	

}

class OutOfBoundsException extends Exception{}
