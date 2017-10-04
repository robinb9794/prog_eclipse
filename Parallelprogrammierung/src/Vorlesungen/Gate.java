package Vorlesungen;

public class Gate extends Thread {
	private Counter globalCounter;
	private Counter localCounter;
	private final int MAX_VISITORS = 20;

	public Gate(String name, Counter globalCounter) {
		// Konstruktor der Oberklasse Thread aufrufen
		super("Tor \"" + name + "\"");
		// Zaehler zuweisen
		this.globalCounter = globalCounter;
		this.localCounter = new Counter();
	}

	public int getLocalValue() {
		return localCounter.getValue();
	}

	public void run() {
		System.out.println(getName() + ": oeffne...");
		for (int i = 0; i < MAX_VISITORS; i++) {
			localCounter.incrementValue();
			globalCounter.incrementValue();
		}
		System.out.println(getName() + ": schließe.");
	}

	public static void main(String args[]) {
		GoodCounter firstCounter = new GoodCounter();
		Gate east = new Gate("Osten", firstCounter);
		Gate west = new Gate("Westen", firstCounter);
		east.start();
		west.start();
		// jetzt laufen drei Threads

		// auf Beendigung der Gate-Threads warten
		try {
			east.join();
			west.join();
		} catch (Exception e) {
			System.out.println(">>>Exception: " + e);
		}

		System.out.println();
		System.out.println(east.getName() + ": lokal " + east.getLocalValue() + " Besucher gezaehlt.");
		System.out.println(west.getName() + ": lokal " + west.getLocalValue() + " Besucher gezaehlt.");
		System.out.println("Zaehler: global " + firstCounter.getValue() + " Besucher gezaehlt.");
	}
}

class Counter {
	private int value;; // aktueller Zaehlerstand

	public Counter() {
		this.value = 0;
	}

	public void incrementValue() {
		int temp = value + 1;
		{ // eventuell Prozesswechsel vornehmen
			try {
				if (Math.random() > 0.5) {
					Thread.yield();
				}
			} catch (Exception e) {

			}
		}
		value = temp;
	}

	public int getValue() {
		return value;
	}
}

class GoodCounter extends Counter {
	public synchronized void incrementValue() {
		super.incrementValue();
	}

	public synchronized int getValue() {
		return super.getValue();
	}
}
