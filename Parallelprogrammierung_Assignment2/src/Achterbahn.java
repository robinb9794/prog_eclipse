
public class Achterbahn {
	public static void main(String args[]) {
		System.out.println(">>> Eine Achterbahn auf dem Bremerhavener Freimarkt <<<");

		Wagen w = new Wagen(6, "WAGEN");
		for (int i = 0; i < 3; i++) {
			new Thread(new Steuerung(w, "STEUERUNG")).start();
		}

	}
}

class Wagen extends Thread {
	private int M;
	private int freiePlaetze;

	public Wagen(int seats, String name) {
		super(name);
		setM(seats);
		setFreiePlaetze(seats);
	}

	public synchronized int getFreiePlaetze() {
		return freiePlaetze;
	}

	public synchronized void setFreiePlaetze(int freiePlaetze) {
		this.freiePlaetze = freiePlaetze;
	}

	public synchronized int getM() {
		return M;
	}

	public synchronized void setM(int M) {
		this.M = M;
	}

	public void run() {
		try {
			abfahrt();
		} catch (InterruptedException e) {
		}
	}

	public void abfahrt() throws InterruptedException {
		synchronized (this) {
			while (!(getFreiePlaetze() == 0)) {
				this.wait();
			}
			System.out.println(getName() + ": Wagen fährt los.\n" + getName() + ": *Kreisch* *Schrei*");
			ankunft();
		}

	}

	public void ankunft() {
		System.out.println(getName() + ": Wagen kommt wieder an.");
		aussteigen();
	}

	public void aussteigen() {
		System.out.println(getName() + ": Alle Passagiere steigen aus.");
		nochEineRunde();
	}

	public void nochEineRunde() {
		System.out.println(getName() + ": Noch eine Runde?");
		setFreiePlaetze(getM());
		this.notifyAll();
	}
}

class Steuerung extends Thread {
	Wagen w;
	Drehkreuz d;

	public Steuerung(Wagen w, String name) {
		super(name);
		this.w = w;
		d = new Drehkreuz(w, "DREHKREUZ");

		new Thread(w).start();
	}

	public void run() {
		try {
			go();
		} catch (InterruptedException e) {
		}
	}

	public void go() throws InterruptedException {
		synchronized (w) {
			while (!(w.getFreiePlaetze() > 0)) {
				w.wait();
			}
			while (!(w.getFreiePlaetze() == 0)) {
				d.durchgehen();
			}
			System.out.println(getName() + ": Wagen wird benachrichtigt, dass er nun voll ist.");
			w.notifyAll();
		}

	}
}

class Drehkreuz {
	Wagen w;
	String name;

	public Drehkreuz(Wagen w, String name) {
		this.w = w;
		this.name = name;
	}

	public void durchgehen() throws InterruptedException {
		w.setFreiePlaetze(w.getFreiePlaetze() - 1);
		System.out.println(
				name + ": Passagier geht durch das Drehkreuz. Nur noch " + w.getFreiePlaetze() + " freie Plätze.");
	}
}
