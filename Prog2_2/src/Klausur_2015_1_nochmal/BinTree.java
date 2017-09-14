package Klausur_2015_1_nochmal;

public class BinTree {
	static class Node {
		public Node(int i) {
			value = i;
		}

		int value;
		Node left, right;
	}

	private Node root = null;

	public void insert(int value) {
		if (root == null) {
			root = new Node(value);
		} else {
			insert(root, value);
		}
	}

	public void insert(Node node, int value) {
		if (node.value > value) {
			if (node.left == null) {
				node.left = new Node(value);
			} else {
				insert(node.left, value);
			}
		} else {
			if (node.right == null) {
				node.right = new Node(value);
			} else {
				insert(node.right, value);
			}
		}
	}

	public void insert() {
		insert(5);
		insert(1);
		insert(10);
		insert(3);
		insert(8);
		insert(11);
		insert(5);
		insert(5);
		insert(20);
		insert(0);
	}

	public static void main(String args[]) {
		BinTree tree = new BinTree();
		tree.insert();
		float f = tree.avg_2();
		System.out.println(f);
	}

	public float avg_2() {
		int counterNodes = countNodes_2(root);
		int countContent = countContent_2(root, 0);
		return countContent / counterNodes;
	}

	public int countNodes_2(Node n) {
		return n != null ? 1 + countNodes_2(n.left) + countNodes_2(n.right) : 0;
	}

	public int countContent_2(Node n, int c) {
		if (n != null) {
			c = c + n.value;
			if (n.left != null) {
				c = countContent_2(n.left, c);
			}
			if (n.right != null) {
				c = countContent_2(n.right, c);
			}
		}

		return c;
	}

	public float avg() {
		int countNodes = countNodes(root);
		int countContent = countContent(root, 0);
		float avg = countContent / countNodes;
		return avg;
	}

	public int countNodes(Node n) {
		return n == null ? 0 : 1 + countNodes(n.left) + countNodes(n.right);
	}

	public int countContent(Node n, int c) {
		if (n != null) {
			if (n.left != null) {
				c = countContent(n.left, c);
			}
			if (n.right != null) {
				c = countContent(n.right, c);
			}
		}
		return c + n.value;
	}
}
