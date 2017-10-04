package Uebung5;

public class HelloClass {
	private String greeting;

	public HelloClass(String name) {
		this.greeting = ">>> Hello " + name + "!<<<";
	}

	public void printNormal() {
		printIt(greeting);
	}

	public void printLower() {
		printIt(greeting.toLowerCase());
	}

	public void printUpper() {
		printIt(greeting.toUpperCase());
	}

	protected void printIt(String str) {
		for (int i = 0; i < str.length(); i++) {
			try {
				Thread.sleep(Math.round(Math.random()) * 100);
			} catch (Exception e) {

			}
			System.out.print(str.substring(i, i + 1));
		}
		System.out.println();
	}
}

class HelloNormalThread extends Thread {
	HelloClass hc;

	public HelloNormalThread(HelloClass hc) {
		this.hc = hc;
	}

	public void run() {
		hc.printNormal();
	}
}

class HelloLowerThread extends Thread {
	HelloClass hc;

	public HelloLowerThread(HelloClass hc) {
		this.hc = hc;
	}

	public void run() {
		hc.printLower();
	}
}

class HelloUpperThread extends Thread {
	HelloClass hc;

	public HelloUpperThread(HelloClass hc) {
		this.hc = hc;
	}

	public void run() {
		hc.printUpper();
	}
}

class HelloClassAtomic extends HelloClass {
	public HelloClassAtomic(String str) {
		super(str);
	}

	public synchronized void printNormal() {
		super.printNormal();
	}

	public synchronized void printLower() {
		super.printLower();
	}

	public synchronized void printUpper() {
		super.printUpper();
	}
}
