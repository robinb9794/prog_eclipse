package Klausur_Probe_Daniel;

import java.util.Random;

public class BinTree {
	static class Node {
		public Node(int i) {
			m_Key = i;
		}

		int m_Key;
		Node m_Left = null;
		Node m_Right = null;
	}

	private Node m_Root = null;

	public void insert(int key) {
		if (m_Root == null) {
			m_Root = new Node(key);
		} else {
			insert(m_Root, key);
		}
	}

	public void insert(Node n, int key) {
		if (n.m_Key > key) {
			if (n.m_Left == null) {
				n.m_Left = new Node(key);
			} else {
				insert(n.m_Left, key);
			}
		} else {
			if (n.m_Right == null) {
				n.m_Right = new Node(key);
			} else {
				insert(n.m_Right, key);
			}
		}
	}

	public void print(Node n) {
		System.out.print(n.m_Key + " | ");
		if (n.m_Left != null) {
			print(n.m_Left);
		}
		if (n.m_Right != null) {
			print(n.m_Right);
		}
	}

	public void push(SimpleCollection<Integer> c) {
		pushEven(c, m_Root);
		pushOdd(c, m_Root);
	}

	public void pushEven(SimpleCollection<Integer> c, Node n) {
		if (n != null) {
			if (n.m_Right != null) {
				pushEven(c, n.m_Right);
			}
			if (n.m_Key % 2 == 0) {
				c.push_front(n.m_Key);
			}
			if (n.m_Left != null) {
				pushEven(c, n.m_Left);
			}
		}
	}

	public void pushOdd(SimpleCollection<Integer> c, Node n) {
		if (n != null) {
			if (n.m_Left != null) {
				pushOdd(c, n.m_Left);
			}
			if (n.m_Key % 2 != 0) {
				c.push_front(n.m_Key);
			}
			if (n.m_Right != null) {
				pushOdd(c, n.m_Right);
			}

		}
	}

	public void push2(SimpleCollection<Integer> c) {
		pushEven2(c, m_Root);
		pushOdd2(c, m_Root);
	}

	public void pushEven2(SimpleCollection<Integer> c, Node n) {
		if (n != null) {
			if (n.m_Right != null) {
				pushEven2(c, n.m_Right);
			}
			if (n.m_Key % 2 == 0) {
				c.push_front(n.m_Key);
			}
			if (n.m_Left != null) {
				pushEven2(c, n.m_Left);
			}
		}
	}

	public void pushOdd2(SimpleCollection<Integer> c, Node n) {
		if (n != null) {
			if (n.m_Left != null) {
				pushOdd2(c, n.m_Left);
			}
			if (n.m_Key % 2 != 0) {
				c.push_front(n.m_Key);
			}
			if (n.m_Right != null) {
				pushOdd2(c, n.m_Right);
			}
		}
	}

	public void insert(BinTree tree) {
		tree.insert(5);
		tree.insert(1);
		tree.insert(10);
		tree.insert(3);
		tree.insert(8);
		tree.insert(11);
		tree.insert(5);
		tree.insert(5);
		tree.insert(20);
		tree.insert(0);
	}

	public static void main(String args[]) {
		BinTree a2 = new BinTree();
		for (int i = 0; i < 10; i++) {
			a2.insert(new Random().nextInt(20));
		}
		a2.print(a2.m_Root);
		System.out.println();
		DoubleList<Integer> dl = new DoubleList<Integer>();
		a2.push2(dl);
		dl.print();
	}
}
