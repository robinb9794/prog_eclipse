package Probeklausur;

public class A {
	A(){
		this(1);
	}
	A(int i){
		System.out.println(i);
	}
	public static void main(String args[]){
		new B(4);
	}
}

class B extends A{
	B(){
		super(0);
	}
	B(int i){
		super(1-i);
		if(i>0){
			new B(i-1);
			System.out.println(i);
		}else{
			new A(i);
		}
	}
}
