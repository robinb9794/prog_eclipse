package Uebung5;

public class HelloClassMain {
	public static void main(String args[]) {
		HelloClassAtomic hca = new HelloClassAtomic("Max Mustermann");
		HelloNormalThread hnt = new HelloNormalThread(hca);
		HelloLowerThread hlt = new HelloLowerThread(hca);
		HelloUpperThread hut = new HelloUpperThread(hca);

		hnt.start();
		hlt.start();
		hut.start();
	}
}
