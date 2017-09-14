package Klausur_2015_2;

public class SingleList<T> {
	/**
	 * - das letzte eingefügte Element bei "void add(T obj)" ist m_Head - das
	 * letzte eingefügte Elements steht links - Liste wird von links nach rechts
	 * ausgegeben, wobei links immer m_Head ist
	 */

	class ListElem {
		private ListElem m_Next;
		private T m_Elem;

		public ListElem(T obj, ListElem next) {
			m_Elem = obj;
			m_Next = next;
		}

		public T getElement() {
			return m_Elem;
		}

		public ListElem getNext() {
			return m_Next;
		}

		public void setNext(ListElem next) {
			m_Next = next;
		}
	}

	public ListElem m_Head;

	public SingleList() {
		m_Head = null;
	}

	public void print() {
		for (ListElem elem = m_Head; elem != null; elem = elem.getNext()) {
			if (elem.getNext() != null) {
				System.out.print("[" + elem.getElement() + "]--->");
			} else {
				System.out.print("[" + elem.getElement() + "]");
			}
		}
		System.out.println();
		System.out.println("Head: " + m_Head.getElement());
	}

	public void add(T obj) {
		if (m_Head == null) {
			m_Head = new ListElem(obj, null);
		} else {
			m_Head = new ListElem(obj, m_Head);
		}

	}

	public void delete(ListElem pElem2Delete) {
		if (pElem2Delete != null) {
			if (m_Head == pElem2Delete) {
				m_Head = pElem2Delete.getNext();
			} else {
				for (ListElem tmp = m_Head; tmp != null; tmp = tmp.getNext()) {
					if (tmp.getNext() == pElem2Delete) {
						tmp.setNext(pElem2Delete.getNext());
						return;
					}
				}
			}
		}
	}

	public void delete(int start, int end) {
		ListElem elemStart = m_Head;
		int n = 0;
		while (n < start - 1) {
			elemStart = elemStart.m_Next;
			n++;
		}
		ListElem elemEnd = m_Head;
		n = 0;
		while (n <= end) {
			elemEnd = elemEnd.m_Next;
			n++;
		}
		elemStart.m_Next = elemEnd;
	}

	public static void main(String args[]) {
		SingleList<Integer> sl = new SingleList<Integer>();
		sl.add(400);
		sl.add(30);
		sl.add(20);
		sl.add(10);
		sl.add(1);
		sl.add(2);
		sl.add(3);
		sl.print();
		sl.delete(1, 4);
		sl.print();
	}

}
