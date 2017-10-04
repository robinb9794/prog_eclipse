package Klausur_2015_1_nochmal_3;

public class List {
	static class Node {
		Node(int i, Node next, Node prev) {
			m_Key = i;
			m_Next = next;
			m_Prev = prev;
		}

		int m_Key;
		Node m_Next;
		Node m_Prev;
	}

	void push_front(int i) {
		System.out.println("push_front: " + i);
		if (m_Head == null) {
			m_Head = new Node(i, null, null);
			m_Tail = m_Head;
		} else {
			m_Head.m_Prev = new Node(i, m_Head, null);
			m_Head = m_Head.m_Prev;
		}
	}

	void push_back(int i) {
		System.out.println("push_back: " + i);
		if (m_Head == null) {
			m_Head = new Node(i, null, null);
			m_Tail = m_Head;
		} else {
			m_Tail.m_Next = new Node(i, null, m_Tail);
			m_Tail = m_Tail.m_Next;
		}
	}

	void push_back2(int i) {
		System.out.println("push_back: " + i);
		if (m_Head == null) {
			m_Head = new Node(i, null, null);
			m_Tail = m_Head;
		} else {
			m_Tail.m_Next = new Node(i, null, m_Tail);
			m_Tail = m_Tail.m_Next;
		}
	}

	void push_back3(int i) {
		System.out.println("push_back: " + i);
		if (m_Head == null) {
			m_Head = new Node(i, null, null);
			m_Tail = m_Head;
		} else {
			m_Tail.m_Next = new Node(i, null, m_Tail);
			m_Tail = m_Tail.m_Next;
		}
	}

	Node m_Head;
	Node m_Tail;

	public void print() {
		System.out.print("Liste: ");
		for (Node tmp = m_Head; tmp != null; tmp = tmp.m_Next) {
			if (tmp.m_Next != null) {
				System.out.print("[" + tmp.m_Key + "]--->");
			} else {
				System.out.print("[" + tmp.m_Key + "]");
			}
		}
		System.out.println();
		System.out.println("-> Head: " + m_Head.m_Key);
		System.out.println("-> Tail: " + m_Tail.m_Key);
	}

	public static void main(String args[]) {
		List list = new List();
		for (int i = 0; i < 10; i++) {
			list.add(i);
		}
		list.print();
		list.push_back3(-1);
		list.print();
		list.push_front(10);
		list.print();
	}

	public void add(int obj) {
		if (m_Head == null) {
			m_Head = new Node(obj, null, null);
			m_Tail = m_Head;
		} else {
			m_Head = new Node(obj, m_Head, null);
			m_Head.m_Next.m_Prev = m_Head;
		}
	}
}
