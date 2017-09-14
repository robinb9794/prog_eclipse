package Uebung6;

public class Kannibalenspeisung {
	public static void main(String args[]) {
		Buffet buffet = new Buffet();
		new Thread(new Koch(buffet)).start();

		for(int i = 1; i<=20;i++){
			new Thread(new Kannibal("" + i, buffet)).start();
		}
	}
}
