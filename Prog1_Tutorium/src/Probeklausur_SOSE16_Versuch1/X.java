package Probeklausur_SOSE16_Versuch1;

public class X {
	int m1;
	int m2;
	static int x = 1;
	
	X(int i){
		m1 = i+x;
		m2 = x++;
	}
	public void doit(){
		System.out.println(m1+ " "+m2);
	}
	public static void main(String args[]){
		X[] a = {new X(23),new Y(), new Z(3), new Y()};
		for(X x : a){
			x.doit();
		}
	}
}

class Y extends X{
	Y(){
		super(34);
	}
	public void doit(){
		System.out.println("ich bin Y");
		super.doit();
	}
}

class Z extends X{
	Z(int i){
		super(i+2);
	}
}
