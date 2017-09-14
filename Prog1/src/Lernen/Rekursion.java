package Lernen;

public class Rekursion {
	public static void main(String args[]){
		char[] c = {'A','N','N','A'};
		System.out.println(pali(c, 0, c.length));
	}
	
	static boolean pali(char[] c, int i, int j){
		try{
			if(c.length>0 && j>=0){
				return c[i]==c[j] ? pali(c,i+1,j-1) : false;
			}
		}catch(ArrayIndexOutOfBoundsException e){
			
		}
		return false;
	}
}
