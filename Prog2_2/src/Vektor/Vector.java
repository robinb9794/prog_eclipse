package Vektor;

public class Vector<T> implements SimpleCollection<Integer>{

	private Object[] mObjects;
	private final int mIncWidth;
	private int mNextFree;
	private int mPrevFree;
	
	public Vector(int initialCapacity, int capacityIncrement){
		mIncWidth=capacityIncrement;
		mNextFree=0;
		mPrevFree=-1;
		mObjects = new Object[initialCapacity];
	}
	
	public Vector(int initialCapacity){
		this(initialCapacity,0);
	}
	
	public Vector(){
		this(1,0);
	}
	
	public void add(Object obj){
		System.out.println("add: "+obj.toString());
		if(mNextFree>=mObjects.length){
			resize();
		}
		mObjects[mNextFree++]=obj;
	}
	
	public void resize(){
		System.out.println("Vector wird gerade um "+mIncWidth+" erweitert...");
		final int newSize=mIncWidth==0
						? mObjects.length*2 :
						mObjects.length+mIncWidth;
		Object[] newObjects = new Object[newSize];
		
		for(int i =0; i<mObjects.length;++i){
			newObjects[i]=mObjects[i];
		}
		mObjects=newObjects;
	}

	@Override
	public int size() {
		return mNextFree-mPrevFree-1;
	}

	@Override
	public void push_back(Integer arg) {
		System.out.println("push_back: "+arg.toString());
		if(mPrevFree>=0){
			if(mNextFree>=mObjects.length){
				resize();
			}
			for(int i = mPrevFree; i<mObjects.length-1; i++){
				mObjects[i]=mObjects[i+1];
			}
			mPrevFree--;
			mObjects[mNextFree-1]=arg;
		}else{
			System.out.println("Nicht möglich, da vorne kein Platz ist.");
		}		
	}

	@Override
	public void push_front(Integer arg) {
		System.out.println("push_front: "+arg.toString());
		if(mNextFree>=mObjects.length){
			resize();
		}
		
			for(int i = mObjects.length-1; i>0;i--){
				mObjects[i]=mObjects[i-1];
			}
			mObjects[mPrevFree+1]=arg;
			mNextFree++;
			
	}

	@Override
	public Integer get(int i) throws OutOfBoundsException{
		if(i>=0 && i<mObjects.length){
			return (Integer) mObjects[mPrevFree+i+1];
		}else{
			throw new OutOfBoundsException();
		}
	}

	@Override
	public void set(int i, Integer arg) throws OutOfBoundsException{
		System.out.println("set: Position "+i+", arg "+arg.toString());
		if(i>=0 && i<mObjects.length){
			mObjects[mPrevFree+i+1]=arg;
			mNextFree=mPrevFree+i+2;
			mPrevFree+=i;
		}else{
			throw new OutOfBoundsException();
		}
	}

	@Override
	public void delete(int i) throws OutOfBoundsException{
		System.out.println("delete: Position "+i);
		if(mNextFree>=mObjects.length){
			resize();
		}
		if(i>=0 && i<mObjects.length){
			for(int j = i; j<mNextFree; j++){
				mObjects[j]=mObjects[j+1];
			}
			mNextFree--;
		}else{
			throw new OutOfBoundsException();
		}
	}
	
	public void print(){
		for(int i = 0; i<mObjects.length;i++){
			System.out.print("["+i+"]\t");
		}
		System.out.println();
		for(int i = 0; i<mObjects.length;i++){			
			if(mObjects[i]==null){
				System.out.print(" -\t");
			}else{
				System.out.print(" "+mObjects[i] + "\t");
			}
		}
		System.out.println();
		System.out.println("mNextFree:" +mNextFree);
		System.out.println("mPrevFree:" +mPrevFree);
		System.out.println();
	}
	
	public static void main(String arg[]) throws OutOfBoundsException{
		Vector<Integer> vec = new Vector<Integer>(5,5);
		vec.set(2,5);
		vec.add(6);
		vec.add(7);
		vec.add(8);
		System.out.println("Der Vektor hat die Länge "+vec.mObjects.length+" und besteht aus "+vec.size() + " Elementen:");
		
		vec.print();
		vec.push_front(4);
		vec.print();
		vec.push_back(9);
		vec.print();
		vec.push_front(3);
		vec.print();
		vec.push_front(2);
		vec.print();
		vec.push_back(10);
		vec.print();
		vec.push_front(1);
		vec.print();
		vec.add(11);
		vec.print();
	}

}

class OutOfBoundsException extends Exception{}
