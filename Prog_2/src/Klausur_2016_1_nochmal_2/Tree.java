package Klausur_2016_1_nochmal_2;

public class Tree {
	static class Node {
		public Node(int i) {
			m_Key = i;
		}

		int m_Key;
		Node m_Left = null, m_Right = null;
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

	public static void insert(Tree tree) {
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

	public int countNodes(Node n) {
		return n == null ? 0 : 1 + countNodes(n.m_Left) + countNodes(n.m_Right);
	}

	public void deleteLeafs() {
		if (m_Root != null) {
			System.out.println("Lösche nun alle Blätter...");
			deleteLeafs(m_Root);
		}
	}

	public void deleteLeafs(Node n) {
		if (n.m_Left != null) {
			if (n.m_Left.m_Left == null && n.m_Left.m_Right == null) {
				System.out.println("Lösche Blatt " + n.m_Left.m_Key);
				n.m_Left = null;
			} else {
				deleteLeafs(n.m_Left);
			}
		}
		if (n.m_Right != null) {
			if (n.m_Right.m_Left == null && n.m_Right.m_Right == null) {
				System.out.println("Lösche Blatt " + n.m_Right.m_Key);
				n.m_Right = null;
			} else {
				deleteLeafs(n.m_Right);
			}
		}
	}

	public void deleteLeafs_2() {
		deleteLeafs_2(m_Root);
	}

	public void deleteLeafs_2(Node n) {
		if (n.m_Left != null) {
			if (n.m_Left.m_Left == null && n.m_Left.m_Right == null) {
				n.m_Left = null;
			} else {
				deleteLeafs_2(n.m_Left);
			}
		}
		if (n.m_Right != null) {
			if (n.m_Right.m_Left == null && n.m_Right.m_Right == null) {
				n.m_Right = null;
			} else {
				deleteLeafs_2(n.m_Right);
			}
		}
	}

	public void deleteLeafs_3() {
		deleteLeafs_3(m_Root);
	}

	public void deleteLeafs_3(Node n) {
		if (n != null) {
			if (n.m_Left != null) {
				if (n.m_Left.m_Left == null && n.m_Left.m_Right == null) {
					n.m_Left = null;
				} else {
					deleteLeafs_3(n.m_Left);
				}
			}
			if (n.m_Right != null) {
				if (n.m_Right.m_Left == null && n.m_Right.m_Right == null) {
					n.m_Right = null;
				} else {
					deleteLeafs_3(n.m_Right);
				}
			}
		}
	}

	public static void main(String args[]) {
		Tree t = new Tree();
		t.insert(t);
		System.out.println("Der Baum besitzt " + t.countNodes(t.m_Root) + " Knoten.");
		t.deleteLeafs_3();
		System.out.println("Nun besitzt der Baum nur noch " + t.countNodes(t.m_Root) + " Knoten.");
	}
}
