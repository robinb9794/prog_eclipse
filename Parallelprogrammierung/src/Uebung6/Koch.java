package Uebung6;

public class Koch extends Thread {
	Buffet buffet;

	public Koch(Buffet buffet) {
		this.buffet = buffet;
	}

	public void run() {
		try {
			fuelleBuffet();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void koche() {
		System.out.println("Koche...");
	}

	public void fuelleBuffet() throws InterruptedException {
		synchronized (buffet.tisch) {
			while (!(buffet.getI() == 0)) {
				System.out.println("Koch wartet...");
				buffet.tisch.wait();
			}
			koche();
			System.out.println("Fülle Buffet.");
			buffet.setI(buffet.getM());
			buffet.tisch.notifyAll();
			fuelleBuffet();
		}

	}
}
