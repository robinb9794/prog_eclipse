package Uebung6;

public class Kannibal extends Thread {
	String name;
	Buffet buffet;

	public Kannibal(String name, Buffet buffet) {
		this.name = name;
		this.buffet = buffet;
	}

	public void run() {
		try {
			gehEssen();
		} catch (Exception e) {
		}
	}

	public void gehEssen() throws InterruptedException {
		synchronized (buffet.tisch) {
			System.out.println("Kannibal " + name + " geht zum Essen.");
			while (!(buffet.getI() > 0)) {
				System.out.println("Kannibal " + name + " wartet...");
				buffet.tisch.wait();
			}
			buffet.setI(buffet.getI() - 1);
			binZufrieden();
			buffet.tisch.notifyAll();
		}

	}

	public synchronized void binZufrieden() {
		System.out.println("Kannibal " + name + " ist zufrieden.");
	}
}
