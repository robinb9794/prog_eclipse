package Uebung6;

public class Buffet {
	private static final int M = 5;
	private int i = M;
	Object tisch = new Object();

	public synchronized void setI(int i) {
		this.i = i;
	}

	public synchronized int getI() {
		return i;
	}

	public synchronized int getM() {
		return M;
	}
}
