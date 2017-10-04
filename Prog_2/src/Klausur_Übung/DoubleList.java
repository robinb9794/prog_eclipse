package Klausur_Übung;

/**
 * deleteEverySecond & invert für doppelt verkettete Listen
 * @author Robin
 *
 * @param <T>
 */
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

	public void deleteEverySecond() {
		for (ListElem tmp = m_Head; tmp != null; tmp = tmp.getNext()) {
			try {
				tmp.m_Next = tmp.m_Next.m_Next;
				tmp.m_Next.m_Prev = tmp;
			} catch (Exception e) {
				tmp.m_Next = null;
			} finally {
				setM_Tail(tmp);
			}
		}
	}

	public void deleteEverySecond2() {
		for (ListElem tmp = m_Head; tmp != null; tmp = tmp.m_Next) {
			try {
				tmp.m_Next = tmp.m_Next.m_Next;
				tmp.m_Next.m_Prev = tmp;
			} catch (Exception e) {
				tmp.m_Next = null;
			} finally {
				m_Tail = tmp;
			}
		}
	}

	public void invert() {

		ListElem node = m_Head;
		ListElem temp;

		while (node != null) {
			temp = node.m_Next;
			node.m_Next = node.m_Prev;
			node.m_Prev = temp;

			node = temp;
		}
		temp = m_Head;
		m_Head = m_Tail;
		m_Tail = temp;
	}

	public void invert2() {
		ListElem node = m_Head;
		ListElem tmp;

		while (node != null) {
			tmp = node.m_Next;
			node.m_Next = node.m_Prev;
			node.m_Prev = tmp;

			node = tmp;
		}

		tmp = m_Head;
		m_Head = m_Tail;
		m_Tail = tmp;
	}

	public void deleteEverySecond3() {
		for (ListElem tmp = m_Head; tmp != null; tmp = tmp.m_Next) {
			try {
				tmp.m_Next = tmp.m_Next.m_Next;
				tmp.m_Next.m_Prev = tmp;
			} catch (Exception e) {
				tmp.m_Next = null;
			} finally {
				m_Tail = tmp;
			}
		}
	}

	public void invert3() {
		ListElem node = m_Head;
		ListElem tmp;

		while (node != null) {
			tmp = node.m_Next;
			node.m_Next = node.m_Prev;
			node.m_Prev = tmp;

			node = tmp;
		}

		tmp = m_Head;
		m_Head = m_Tail;
		m_Tail = tmp;
	}

	public static void main(String args[]) throws OutOfBoundsException {
		DoubleList<Integer> list = new DoubleList<Integer>();
		for (int i = 0; i < 10; i++) {
			list.add(i);
		}
		list.print();
		list.add(11);
		list.print();
		System.out.println(list.get(1));
		list.set(5, 30);
		list.print();
		list.push_back(22);
		list.print();
		list.deleteEverySecond2();
		list.print();
		list.invert();
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