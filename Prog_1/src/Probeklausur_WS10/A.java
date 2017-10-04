package Probeklausur_WS10;

public class A {
	protected int i;
	protected char c;
	
	A(int i, char c){
		this.i=i;
		this.c=c;
	}
	
	void juhu(){
		System.out.println(this.i+" "+this.c);
	}
	
	public static void main(String args[]){
		A[] a = {new A(3,'n'), new B(5,'#'),new C()};
		for(A x : a){
			x.juhu();
			System.out.println();
		}
	}
}

class B extends A{
	boolean b;
	
	B(int i, char c){
		super(i,c);
	}
	
	void juhu(){
		System.out.println(this.i+ " "+this.c+"\n"+this.b);
	}
}

class C extends B{
	C(){
		super(17,'?');
	}
	
	void juhu(){
		System.out.println("Ich bin C\n"+this.i+ " "+this.c+"\n"+this.b);
	}
}
