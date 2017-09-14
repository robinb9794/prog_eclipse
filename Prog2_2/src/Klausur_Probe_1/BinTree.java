package Klausur_Probe_1;

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
		System.out.println(tree.find_2(32));
		System.out.println(tree.isSorted());
	}

	public boolean find(int i) {
		return find(i, root, false);
	}

	public boolean find(int i, Node n, boolean found) {
		if (n != null) {
			if (n.value == i) {
				found = true;
			}
			if (n.left != null) {
				found = find(i, n.left, found);
			}
			if (n.right != null) {
				found = find(i, n.right, found);
			}
		}
		return found;
	}

	public boolean find_2(int i) {
		return find_2(i, root, false);
	}

	public boolean find_2(int i, Node n, boolean found) {
		if (n.value == i) {
			found = true;
		}
		if (n.left != null) {
			found = find_2(i, n.left, found);
		}
		if (n.right != null) {
			found = find_2(i, n.right, found);
		}
		return found;
	}

	public boolean isSorted() {
		return isSorted(root, false);
	}

	public boolean isSorted(Node n, boolean sorted) {
		if (n != null) {
			if (n.left != null) {
				if (n.left.value < n.value) {
					sorted = isSorted(n.left, true);
				}
			}
			if (n.right != null) {
				if (n.right.value > n.value) {
					sorted = isSorted(n.right, true);
				}
			}
		}
		return sorted;
	}
}
