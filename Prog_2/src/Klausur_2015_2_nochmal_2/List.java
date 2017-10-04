package Klausur_2015_2_nochmal_2;

public class List<T> {
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

	public List() {
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

	public void delete2(int start, int end) {
		if (start < end && m_Head != null) {
			ListElem tmp = m_Head;
			ListElem tmp2 = m_Head;
			for (int i = 0; i < start; i++) {
				tmp = tmp.m_Next;
			}
			for (int i = 0; i < start - 1; i++) {
				tmp2 = tmp2.m_Next;
			}
			for (int i = 0; i < end; i++) {
				tmp = tmp.m_Next;
			}
			tmp2.m_Next = tmp;
		}
	}

	public void delete3(int start, int end) {
		if (start <= end) {
			ListElem elemStart = m_Head, elemEnd = m_Head;
			for (int i = 0; i < start - 1; i++) {
				elemStart = elemStart.m_Next;
			}
			for (int i = 0; i < end + 1; i++) {
				elemEnd = elemEnd.m_Next;
			}
			elemStart.m_Next = elemEnd;
		}

	}

	public static void main(String args[]) {
		List<Integer> sl = new List<Integer>();
		for (int i = 0; i < 8; ++i) {
			sl.add(i);
		}
		sl.print();
		sl.delete3(1, 4);
		sl.print();
	}
}
