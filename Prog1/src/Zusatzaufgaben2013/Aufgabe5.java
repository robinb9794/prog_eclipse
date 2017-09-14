package Zusatzaufgaben2013;

public class Aufgabe5 {
	public static void main(String args[]){
		A[] a = new A[6];
		for(int i = 0; i<a.length;++i){
			if(i%2==0){
				a[i]=new A(1<<i);
			}else{
				a[i]=new B(i<<1);
			}
		}
		for(A i : a){
			i.doit();
			i.print();
		}
	}
}

class A{
	int m_i;
	A(int a){
		m_i=a;
	}
	void doit(){
		m_i=m_i^8;
	}
	void print(){
		System.out.println(m_i);
	}
}

class B extends A{
	B(int a){
		super(a>>1);
	}
	void doit(){
		++m_i;
	}
}
