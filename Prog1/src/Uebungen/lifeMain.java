package Uebungen;

public class lifeMain {
	public static void main(String args[]){
		Life life = new Life();
		
		while(true){
			life.empty();
			life.ausgabe();
			life.waitOneSecond();
			life.calculateNew();
		}
	}
}
