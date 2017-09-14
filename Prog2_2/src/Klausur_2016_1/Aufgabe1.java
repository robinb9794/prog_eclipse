package Klausur_2016_1;

public class Aufgabe1 {
	private Object[] mObjects = new Object[1];
	private int mNextFree = 0;

	public void add(Object obj) {
		if (mNextFree >= mObjects.length) {
			resize();
		}
		mObjects[mNextFree++] = obj;
	}

	public void resize() {
		Object[] newObjects = new Object[mObjects.length * 2];
		for (int i = 0; i < mObjects.length; ++i) {
			newObjects[i] = mObjects[i];
		}
		mObjects = newObjects;
	}

	public void print() {
		for (int i = 0; i < mObjects.length; i++) {
			System.out.print("[" + i + "]\t");
		}
		System.out.println();
		for (int i = 0; i < mObjects.length; i++) {
			if (mObjects[i] == null) {
				System.out.print(" -\t");
			} else {
				System.out.print(" " + mObjects[i] + "\t");
			}
		}
		System.out.println();
		System.out.println("mNextFree:" + mNextFree);
		System.out.println();
	}

	public void deleteEverySecond() {
		int index = 0;
		for (int i = 0; i < mNextFree; i++) {
			try {
				mObjects[i] = mObjects[i + index];
			} catch (Exception e) {
				mObjects[i] = null;
			}
			++index;
		}

		for (int i = 0; i < mNextFree; i++) {
			if (mObjects[i] == null) {
				mNextFree = i;
				break;
			}
		}

	}

	public static void main(String args[]) {
		Aufgabe1 a1 = new Aufgabe1();
		for (int i = 1; i < 15; i++) {
			a1.add(i);
		}
		a1.print();
		System.out.println("deleting every second...");
		a1.deleteEverySecond();
		a1.print();
	}
}
