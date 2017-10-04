package Klausur_2011_1;

public class DoubleList<T> {
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

	public int size() {
		int counter = 0;
		for (ListElem<T> tmp = getM_Head(); tmp != null; tmp = tmp.getNext()) {
			++counter;
		}
		return counter;
	}

	public void push_back(T arg) {
		System.out.println("push_back: " + arg);

		if (getM_Head() == null) {
			setM_Head(new ListElem(arg, null, null));
			setM_Tail(getM_Head());
		} else {
			setM_Tail(new ListElem(arg, null, getM_Tail()));
		}

	}

	public void push_front(T arg) {
		System.out.println("push_front: " + arg);

		if (getM_Head() == null) {
			setM_Head(new ListElem(arg, null, null));
			setM_Tail(getM_Head());
		} else {
			setM_Head(new ListElem(arg, getM_Head(), null));
		}

	}

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
		list.invert();
		list.print();
	}

	public void invert() {
		System.out.println("inverting...");
		ListElem elem = m_Tail;
		ListElem speicher = null;
		m_Head = elem;
		while (m_Tail != null || elem != null) {
			elem.setNext(m_Tail.getPrev());
			elem.setPrev(m_Tail.getNext());
			speicher = elem;
			elem = elem.getNext();
			m_Tail = m_Tail.getPrev();
		}
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
