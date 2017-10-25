package Aufgabe_3;

public class TestAusgabe {
	public static void main(String args[]){
		int x = 200;
		int n = 2;
		int i;
		boolean flag;
		while(n<=x){
			flag=true;
			i=2;
			while(i<n && flag){
				if(n%i==0){
					flag=false;
				}
				++i;
			}
			if(flag){
				System.out.println(n);
			}
			++n;
		}
	}
}

