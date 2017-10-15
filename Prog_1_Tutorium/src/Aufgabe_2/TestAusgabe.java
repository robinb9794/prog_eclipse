package Aufgabe_2;

public class TestAusgabe {
	public static void main(String args[]){
		boolean a = !(true&&false) && !false, b = !(!a);
		if(!a||b){
			{
				a=!a&&true;
				b=!a;
				{
					System.out.println("juhu");
					System.out.println("a: "+a+", b:"+b);
					if(b){
						System.out.println("noch mal juhu");
					}
				}				
			}
			;
		}
		if(a){
			{
				;
			}
			System.out.println("cool");
			System.out.println("a: "+a+", b:"+b);
		}
	}
}
