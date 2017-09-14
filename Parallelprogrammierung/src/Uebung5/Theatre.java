package Uebung5;

public class Theatre {
	private final int seats = 20;
	private int freePlaces;
	private int[] reservedPlaces;
	private int index;

	public Theatre() {
		freePlaces = seats;
		reservedPlaces = new int[seats];
		index = 0;
	}

	public static void main(String args[]) {
		Theatre th = new Theatre();
		for (int i = 1; i <= 5; i++) {
			Employee e = new Employee(i, th);
			e.start();
		}
	}

	public synchronized void printFreePlaces(String name) {
		System.out.println(name + " is checking free places: " + this.freePlaces);
	}

	public synchronized void printReservedPlaces(String name) {
		System.out.print(name + " is printing reserved places: ");
		for (int i = 0; i < index; i++) {
			System.out.print(reservedPlaces[i] + " ");
		}
		System.out.println();
	}

	public synchronized void reservePlace(String name, int place) {
		reservedPlaces[index] = place;
		System.out.println(name + " is reserving place " + place);
		--freePlaces;
		++index;
	}

	public synchronized int getANewPlace(String name) {
		System.out.println(name + " is getting a new place");
		int place = (int) (Math.random() * seats + 1);
		while (placeIsAlreadyTaken(name, place)) {
			place = (int) (Math.random() * seats + 1);
			System.out.println(name + " is getting a new place " + place);
		}
		return place;
	}

	public synchronized boolean placeIsAlreadyTaken(String name, int place) {
		System.out.println(name + " is checking if place " + place + " is already taken");
		boolean taken = false;
		for (int i = 0; i < index; i++) {
			if (reservedPlaces[i] == place) {
				taken = true;
				System.out.println(name + " ERROR place " + place + " is already taken");
			}
		}
		return taken;
	}
}

class Employee extends Thread {
	String name;
	Theatre th;

	public Employee(int n, Theatre th) {
		this.name = ">> Employee " + n + " <<";
		this.th = th;
	}

	public void run() {
		th.printFreePlaces(name);
		th.reservePlace(name, th.getANewPlace(name));
		th.printReservedPlaces(name);
	}
}
