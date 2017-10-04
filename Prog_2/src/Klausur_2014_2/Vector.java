package Klausur_2014_2;

public class Vector {
	private Object[] mObjects;
	private final int mIncWidth;
	private int mNextFree;

	public Vector(int initialCapacity, int capacityIncrement) {
		mIncWidth = capacityIncrement;
		mNextFree = 0;
		mObjects = new Object[initialCapacity];
	}

	public Vector(int initialCapacity) {
		this(initialCapacity, 0);
	}

	public Vector() {
		this(1, 0);
	}

	public void add(Object obj) {
		if (mNextFree >= mObjects.length) {
			resize();
		}
		mObjects[mNextFree++] = obj;
	}

	public void resize() {
		final int newSize = mIncWidth == 0 ? mObjects.length * 2 : mObjects.length + mIncWidth;
		Object[] newObjects = new Object[newSize];

		for (int i = 0; i < mObjects.length; ++i) {
			newObjects[i] = mObjects[i];
		}
		mObjects = newObjects;
	}

	public int size() {
		return mNextFree;
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

	public static Vector merge(Vector vec1, Vector vec2) {
		Vector vec3 = new Vector(vec1.size() + vec2.size());

		int i_vec1 = 0, j_vec2 = 0;
		boolean vec1_isMax = false, vec2_isMax = false;

		for (int i = 0; i < vec3.mObjects.length; i++) {
			int get_vec1 = i_vec1 < vec1.mNextFree ? vec1.get(i_vec1) : vec1.get(vec1.mNextFree - 1);
			int get_vec2 = j_vec2 < vec2.mNextFree ? vec2.get(j_vec2) : vec2.get(vec2.mNextFree - 1);

			if (i_vec1 == vec1.mNextFree) {
				vec1_isMax = true;
			}

			if (j_vec2 == vec2.mNextFree) {
				vec2_isMax = true;
			}

			if ((get_vec1 <= get_vec2 && !(vec1_isMax)) || vec2_isMax) {
				System.out.println(get_vec1 + " wird eingefügt.");
				vec3.add(get_vec1);
				++i_vec1;
			} else if ((get_vec2 <= get_vec1 && !(vec2_isMax)) || vec1_isMax) {
				System.out.println(get_vec2 + " wird eingefügt.");
				vec3.add(get_vec2);
				++j_vec2;
			}
		}

		return vec3;
	}

	public Integer get(int i) {
		return (Integer) mObjects[i];
	}

	public static void main(String args[]) {
		Vector vec1 = new Vector();
		vec1.add(0);
		vec1.add(2);
		vec1.add(6);
		vec1.add(7);
		vec1.add(10);

		Vector vec2 = new Vector();
		vec2.add(1);
		vec2.add(3);
		vec2.add(4);
		vec2.add(5);
		vec2.add(6);
		vec2.add(8);
		vec2.add(9);

		System.out.println("vec1:");
		vec1.print();

		System.out.println("vec2:");
		vec2.print();

		Vector vec3 = merge(vec1, vec2);
		System.out.println("vec3:");
		vec3.print();
		vec3.add(11);
		vec3.print();
	}
}
