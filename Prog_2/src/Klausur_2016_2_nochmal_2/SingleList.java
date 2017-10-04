package Klausur_2016_2_nochmal_2;

public class SingleList<T> {
	private ListElem m_Head = null;

	class ListElem {
		private ListElem m_Next;
		private T m_Elem;

		public ListElem(T obj, ListElem next) {
			m_Next = next;
			m_Elem = obj;
		}
	}

	public void push_front(T obj) {
		m_Head = new ListElem(obj, m_Head);
	}

	public void deleteEverySecond() {
		for (ListElem tmp = m_Head; tmp != null; tmp = tmp.m_Next) {
			try {
				tmp.m_Next = tmp.m_Next.m_Next;
			} catch (Exception e) {
				tmp = null;
			}
		}
	}

	public static void main(String args[]) {
		SingleList<Integer> sl = new SingleList<Integer>();
		for (int i = 0; i < 6; i++) {
			sl.push_front(i);
		}
		sl.print();
		sl.deleteEverySecond();
		sl.print();
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
