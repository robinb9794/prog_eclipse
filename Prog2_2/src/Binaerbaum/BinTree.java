package Binaerbaum;

import java.util.Random;

public class BinTree {
	class Node {
		int m_Key;
		Node m_Left = null;
		Node m_Right = null;

		public Node(int key) {
			m_Key = key;
		}
	}

	private Node m_Root = null;

	public static void main(String args[]) {
		BinTree tree = new BinTree();
		int r = 20;
		Random ran = new Random();
		for (int i = 0; i < 10; i++) {
			tree.insert(ran.nextInt(r));
		}
		tree.print();
		System.out.println(tree.countNodes() + " nodes");
		System.out.println("average " + tree.average());
		System.out.println("min " + tree.minMax()[0] + ", max " + tree.minMax()[1]);
		tree.deleteLeafs();
		tree.print();
		System.out.println(tree.countNodes() + " nodes");
	}

	public int countNodes() {
		return countNodes(m_Root);
	}

	public int countNodes(Node n) {
		return n == null ? 0 : 1 + countNodes(n.m_Left) + countNodes(n.m_Right);
	}

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

	public float average() {
		return addingContent(m_Root, 0) / countNodes(m_Root);
	}

	public int addingContent(Node n, int c) {
		if (n != null) {
			c += (int) n.m_Key;
			if (n.m_Left != null) {
				c = addingContent(n.m_Left, c);
			}
			if (n.m_Right != null) {
				c = addingContent(n.m_Right, c);
			}
		}
		return c;
	}

	public int[] minMax() {
		int[] mm = { Integer.MAX_VALUE, Integer.MIN_VALUE };
		return minMax(m_Root, mm);
	}

	public int[] minMax(Node n, int[] mm) {
		if (n != null) {
			if (n.m_Key < mm[0]) {
				mm[0] = n.m_Key;
			}
			if (n.m_Key > mm[1]) {
				mm[1] = n.m_Key;
			}
			if (n.m_Left != null) {
				mm = minMax(n.m_Left, mm);
			}
			if (n.m_Right != null) {
				mm = minMax(n.m_Right, mm);
			}
		}
		return mm;
	}

	public void deleteLeafs() {
		if (m_Root != null) {
			System.out.println("Deleting leafs...");
			deleteLeafs(m_Root);
		}
	}

	public void deleteLeafs(Node n) {
		if (n != null) {
			if (n.m_Left != null) {
				if (n.m_Left.m_Left == null && n.m_Left.m_Right == null) {
					n.m_Left = null;
				} else {
					deleteLeafs(n.m_Left);
				}
			}
			if (n.m_Right != null) {
				if (n.m_Right.m_Left == null && n.m_Right.m_Right == null) {
					n.m_Right = null;
				} else {
					deleteLeafs(n.m_Right);
				}
			}
		}
	}

	public void print() {
		print(m_Root);
		System.out.println();
	}

	public void print(Node n) {
		if (n != null) {
			System.out.print(n.m_Key + " | ");
			if (n.m_Left != null) {
				print(n.m_Left);
			}
			if (n.m_Right != null) {
				print(n.m_Right);
			}
		}
	}

	public boolean find(int i) {
		return find(i, m_Root);
	}

	public boolean find(int i, Node n) {
		if (n != null) {
			if (n.m_Key == i) {
				return true;
			}
			if (n.m_Left != null) {
				return find(i, n.m_Left);
			}
			if (n.m_Right != null) {
				return find(i, n.m_Right);
			}
		}
		return false;
	}

}
