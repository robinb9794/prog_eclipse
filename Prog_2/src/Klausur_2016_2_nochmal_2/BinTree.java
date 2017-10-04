package Klausur_2016_2_nochmal_2;

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
		System.out.println(n.m_Key);
		if (n.m_Left != null) {
			System.out.println("gehe nach links...");
			print(n.m_Left);
			System.out.println("gehe wieder hoch zur " + n.m_Key + "...");
		}
		if (n.m_Right != null) {
			System.out.println("gehe nach rechts...");
			print(n.m_Right);
			System.out.println("gehe wieder hoch zur " + n.m_Key + "...");
		}
	}

	int[] minMax() {
		int[] mm = { -999999999, 999999999 };
		return minMaxHelp(m_Root, mm);
	}

	int[] minMaxHelp(Node n, int[] mm) {
		if (n != null) {
			if (n.m_Key > mm[0]) {
				mm[0] = n.m_Key;
			}
			if (n.m_Key < mm[1]) {
				mm[1] = n.m_Key;
			}
			if (n.m_Left != null) {
				mm = minMaxHelp(n.m_Left, mm);
			}
			if (n.m_Right != null) {
				mm = minMaxHelp(n.m_Right, mm);
			}
		}
		return mm;
	}

	public static void main(String args[]) {
		BinTree a2 = new BinTree();
		int r = 20;
		Random ran = new Random();
		for (int i = 0; i < 10; i++) {
			a2.insert(ran.nextInt(r));
		}
		a2.print(a2.m_Root);
		int[] mm = a2.minMax();
		System.out.print("Maximum: " + mm[0] + ", Minimum: " + mm[1]);
	}
}
