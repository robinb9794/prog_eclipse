package Zusatzaufgaben2013;

public class Aufgabe2 {
	public static void main(String args[]){
		char[] wort = {'H','a','u','s'};
		char[] buchstaben1 = {'X','Y','Q','u','S','s','a','H'};
		char[] buchstaben2 = {'Z','m','Q','V','H'};
		
		scrabble(wort, buchstaben1);
		scrabble(wort, buchstaben2);
	}
	
	static void scrabble(char[] wort, char[] buchstaben){
		int n=0;
		for(int i = 0; i<wort.length;i++){
			for(int j = 0; j<buchstaben.length;j++){
				if(wort[i]==buchstaben[j]){
					++n;
				}
			}
		}
		if(n==wort.length){
			System.out.println("Das Wort lässt sich aus den Buchstaben generieren.");
		}else{
			System.out.println("Das Wort lässt sich nicht aus den Buchstaben generieren.");
		}
	}
}
