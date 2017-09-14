package Listen;

public class DoubleList<T> implements SimpleCollection<T> {
	class ListElem<T> {
		private ListElem<T> m_Next;
		private ListElem<T> m_Prev;
		private T m_Elem;

		public ListElem(T obj, ListElem<T> next, ListElem<T> prev) {
			m_Next = next;
			m_Prev = prev;
			m_Elem = obj;

			if (m_Next != null) {
				m_Next.setPrev(this);
			}
			if (m_Prev != null) {
				m_Prev.setNext(this);
			}
		}

		public T getElement() {
			return m_Elem;
		}

		public void setElement(T arg) {
			m_Elem = arg;
		}

		public ListElem<T> getNext() {
			return m_Next;
		}

		public ListElem<T> getPrev() {
			return m_Prev;
		}

		public void setNext(ListElem<T> next) {
			m_Next = next;
		}

		public void setPrev(ListElem<T> prev) {
			m_Prev = prev;
		}
	}

	private ListElem<T> m_Head;
	private ListElem<T> m_Tail;

	public void add(T obj) {
		System.out.println("add: " + obj);
		if (getM_Head() == null) {
			setM_Head(new ListElem<T>(obj, null, null));
			setM_Tail(getM_Head());
		} else {
			setM_Head(new ListElem<T>(obj, getM_Head(), null));
			getM_Head().getNext().setPrev(getM_Head());
		}
	}

	@Override
	public int size() {
		int counter = 0;
		for (ListElem<T> tmp = getM_Head(); tmp != null; tmp = tmp.getNext()) {
			++counter;
		}
		return counter;
	}

	@Override
	public void push_back(T arg) {
		System.out.println("push_back: " + arg);

		if (getM_Head() == null) {
			setM_Head(new ListElem(arg, null, null));
			setM_Tail(getM_Head());
		} else {
			setM_Tail(new ListElem(arg, null, getM_Tail()));
		}

	}

	@Override
	public void push_front(T arg) {
		System.out.println("push_front: " + arg);

		if (getM_Head() == null) {
			setM_Head(new ListElem(arg, null, null));
			setM_Tail(getM_Head());
		} else {
			setM_Head(new ListElem(arg, getM_Head(), null));
		}

	}

	@Override
	public T get(int i) throws OutOfBoundsException {
		System.out.print("get Index " + i + ": ");
		ListElem tmp = getM_Head();

		if (i < 0 || i >= size()) {
			throw new OutOfBoundsException();
		} else if (i == 0) {
			return getM_Head().getElement();
		} else if (i == size() - 1) {
			return getM_Tail().getElement();
		} else {
			for (int j = 1; j <= i; j++) {
				tmp = tmp.getNext();
			}
		}
		return (T) tmp.getElement();
	}

	@Override
	public void set(int i, T arg) throws OutOfBoundsException {
		System.out.println("set Index " + i + ": " + arg.toString());
		ListElem tmp = getM_Head();

		if (i < 0 || i >= size()) {
			throw new OutOfBoundsException();
		} else if (i == 0) {
			getM_Head().setElement(arg);
		} else if (i == size() - 1) {
			getM_Tail().setElement(arg);
		} else {
			for (int j = 0; j < i; j++) {
				tmp = tmp.getNext();
			}
			tmp.setElement(arg);
		}
	}

	@Override
	public void delete(int i) throws OutOfBoundsException {
		System.out.println("delete Index " + i);
		ListElem tmp = getM_Head();

		if (i < 0 || i >= size()) {
			throw new OutOfBoundsException();
		} else if (i == 0) {
			setM_Head(m_Head.getNext());
			getM_Head().setPrev(null);
		} else if (i == size() - 1) {
			setM_Tail(m_Tail.getPrev());
			getM_Tail().setNext(null);
		} else {
			for (int j = 0; j < i; j++) {
				tmp = tmp.getNext();
			}
			tmp.getPrev().setNext(tmp.getNext());
			tmp.getNext().setPrev(tmp.getPrev());
		}

	}

	public void print() {
		System.out.print("Liste: ");
		for (ListElem tmp = getM_Head(); tmp != null; tmp = tmp.getNext()) {
			if (tmp.getNext() != null) {
				System.out.print("[" + tmp.getElement() + "]--->");
			} else {
				System.out.print("[" + tmp.getElement() + "]");
			}
		}
		System.out.println();
		System.out.println("-> " + size() + " Elemente in der Liste");
		System.out.println("-> Head: " + getM_Head().getElement());
		System.out.println("-> Tail: " + getM_Tail().getElement());
	}

	public static void main(String args[]) throws OutOfBoundsException {
		DoubleList<Integer> list = new DoubleList<Integer>();
		for (int i = 0; i < 10; i++) {
			list.add(i);
		}
		list.print();
		list.push_back(-1);
		list.print();
		list.push_front(10);
		list.print();
		list.add(11);
		list.print();
		System.out.println(list.get(1));
		list.set(5, 30);
		list.print();
		list.delete(5);
		list.print();
	}

	public ListElem<T> getM_Head() {
		return m_Head;
	}

	public void setM_Head(ListElem<T> m_Head) {
		this.m_Head = m_Head;
	}

	public ListElem<T> getM_Tail() {
		return m_Tail;
	}

	public void setM_Tail(ListElem<T> m_Tail) {
		this.m_Tail = m_Tail;
	}
}

class OutOfBoundsException extends Exception {
}

class sort {
	public static <T extends Comparable<T>> void shellsort(SimpleCollection<T> col) throws OutOfBoundsException {
		int iDist = 1;
		for (; iDist > 0; iDist /= 3) {
			for (int i1 = iDist; i1 < col.size(); ++i1) {
				final T IVAL = col.get(i1);
				int i2 = i1;
				while (i2 >= iDist && col.get(i2 - iDist).compareTo(IVAL) > 0) {
					col.set(i2, col.get(i2 - iDist));
					i2 = i2 - iDist;
				}
				col.set(i2, IVAL);
			}
		}
	}

	public static <T extends Comparable<T>> void quicksort(SimpleCollection<T> col) throws OutOfBoundsException {
		quicksort_help(col, 0, col.size() - 1);
	}

	public static <T extends Comparable<T>> void quicksort_help(SimpleCollection<T> col, int iLeft, int iRight)
			throws OutOfBoundsException {
		final T MID = col.get((iLeft + iRight) / 2);
		int l = iLeft;
		int r = iRight;

		while (l < r) {
			while (col.get(l).compareTo(MID) < 0) {
				++l;
			}
			while (col.get(r).compareTo(MID) > 0) {
				--r;
			}
			if (l <= r) {
				// swap?
				T tmp = col.get(l);
				col.set(l, col.get(r));
				col.set(r, tmp);
			}
			if (iLeft < r) {
				quicksort_help(col, iLeft, r);
			}
			if (iRight > l) {
				quicksort_help(col, l, iRight);
			}
		}
	}

	static <T extends Comparable<T>> void mergesort(SimpleCollection col) throws OutOfBoundsException {
		mergesort_help(col, 0, col.size() - 1);
	}

	static <T extends Comparable<T>> void mergesort_help(SimpleCollection col, int iLeft, int iRight)
			throws OutOfBoundsException {
		if (iLeft < iRight) {
			final int MIDDLE = (iLeft + iRight) / 2;
			mergesort_help(col, iLeft, MIDDLE);
			mergesort_help(col, MIDDLE + 1, iRight);

			T[] tmp = (T[]) new Comparable[iRight - iLeft + 1];

			for (int i = iLeft; i <= MIDDLE; ++i) {
				tmp[i - iLeft] = (T) col.get(i);
			}

			for (int i = MIDDLE + 1; i <= iRight; ++i) {
				tmp[tmp.length - i + MIDDLE] = (T) col.get(i);
			}

			int iL = 0;
			int iR = tmp.length - 1;

			for (int i = iLeft; i <= iRight; ++i) {
				col.set(i, tmp[iL].compareTo(tmp[iR]) <= 0 ? tmp[iL++] : tmp[iR--]);
			}
		}
	}
}
