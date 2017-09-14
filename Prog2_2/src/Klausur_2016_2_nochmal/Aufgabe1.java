package Klausur_2016_2_nochmal;

public class Aufgabe1<T> {
	private ListElem m_Head = null;

	class ListElem {
		public ListElem(T obj, ListElem next) {
			m_Next = next;
			m_Elem = obj;
		}

		private ListElem m_Next;
		private T m_Elem;
	}

	public void add(T obj) {
		if (m_Head == null) {
			m_Head = new ListElem(obj, null);
		} else {
			m_Head = new ListElem(obj, m_Head);
		}

	}

	public static void main(String args[]) {
		Aufgabe1<Integer> a1 = new Aufgabe1<Integer>();
		for (int i = 6; i >= 0; --i) {
			a1.push_front(i);
		}
		a1.print();
		a1.deleteEverySecond_2();
		a1.print();
	}

	public void push_front(T obj) {
		m_Head = new ListElem(obj, m_Head);
	}

	public void deleteEverySecond() {
		System.out.println("delete every second...");
		if (m_Head != null) {
			for (ListElem tmp = m_Head; tmp != null; tmp = tmp.m_Next) {
				try {
					tmp.m_Next = tmp.m_Next.m_Next;
				} catch (Exception e) {
					tmp.m_Next = null;
				}
			}
		}
	}

	public void deleteEverySecond_2() {
		System.out.println("deleting every second (2)");
		for (ListElem elem = m_Head; elem != null; elem = elem.m_Next) {
			try {
				elem.m_Next = elem.m_Next.m_Next;
			} catch (Exception e) {
				elem.m_Next = null;
			}
		}
	}

	public void print() {
		for (ListElem elem = m_Head; elem != null; elem = elem.m_Next) {
			if (elem.m_Next != null) {
				System.out.print("[" + elem.m_Elem + "]--->");
			} else {
				System.out.print("[" + elem.m_Elem + "]");
			}
		}
		System.out.println();
		System.out.println("Head: " + m_Head.m_Elem);
	}
}
