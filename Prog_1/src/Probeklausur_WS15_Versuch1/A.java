package Probeklausur_WS15_Versuch1;

public class A {
	int i;
	static int j = 0;
	
	A(int i){
		this.i=i;
		System.out.println("A "+i+" "+ ++j);
	}
	void doit(int i){
		System.out.println("doit "+i+" "+this.i+" "+j);
	}
	public static void main(String args[]){
		A[] a = {new A(3), new B(), new C(43)};
		for(int i =0; i<a.length;++i){
			a[i].doit(i);
		}
	}
}

class B extends A{
	B(){
		super(13);
	}
}

class C extends B{
	C(int i){
		this.i=i;
	}
	void doit(int i){
		++j;
		++i;
		System.out.println("juhu");
		super.doit(27);
	}
}

