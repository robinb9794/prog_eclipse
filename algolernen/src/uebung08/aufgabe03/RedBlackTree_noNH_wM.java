package uebung08.aufgabe03;

public class RedBlackTree_noNH_wM<K extends Comparable<K>, D> {

	public class Node {
		public Node(K key, D data) {
			m_Key = key;
			m_Data = data;
			m_bIsRed = true;
		}

		K m_Key;
		D m_Data;
		Node m_Left = null;
		Node m_Right = null;
		boolean m_bIsRed = true;

		public boolean is4Node() {
			return m_Left != null && m_Left.m_bIsRed && m_Right != null
					&& m_Right.m_bIsRed;
		}

		void convert4Node() {
			m_Left.m_bIsRed = false;
			m_Right.m_bIsRed = false;
			m_bIsRed = true;
		}

		boolean isLeaf() {
			return (m_Left == null && m_Right == null) // ist 2er-Blatt

					|| (!m_bIsRed && (

					(m_Right != null
							&& m_Right.m_bIsRed // ist 3er-Blatt 1.fall
							&& m_Right.m_Left == null
							&& m_Right.m_Right == null && m_Left == null)

							|| (m_Left != null
									&& m_Left.m_bIsRed // ist 3er-Blatt 2.fall
									&& m_Left.m_Left == null
									&& m_Left.m_Right == null && m_Right == null)

					|| (m_Left != null && m_Left.m_bIsRed
							&& m_Right != null // ist 4er-Blatt
							&& m_Right.m_bIsRed && m_Left.m_Left == null
							&& m_Left.m_Right == null && m_Right.m_Left == null && m_Right.m_Right == null)));
		}
	}

	public Node m_Root = null;

	void rotateLeft(Node node, Node kind, Node dad) {

		node.m_Right = kind.m_Left;
		kind.m_Left = node;

		boolean sonColor = kind.m_bIsRed;
		kind.m_bIsRed = node.m_bIsRed;
		node.m_bIsRed = sonColor;

		if (dad != null) {
			if (dad.m_Right == node)
				dad.m_Right = kind;
			else
				dad.m_Left = kind;
		}
		if (m_Root == node)
			m_Root = kind;
	}

	void rotateRight(Node node, Node kind, Node dad) {

		node.m_Left = kind.m_Right;
		kind.m_Right = node;

		boolean sonColor = kind.m_bIsRed;
		kind.m_bIsRed = node.m_bIsRed;
		node.m_bIsRed = sonColor;

		if (dad != null) {
			if (dad.m_Right == node)
				dad.m_Right = kind;
			else
				dad.m_Left = kind;
		}
		if (m_Root == node)
			m_Root = kind;
	}

	// methode um zu erkennen ob 2 rote kanten hinteranderer sind ( alle f�lle)
	// die �bernimmt auch jeghliches rotieren
	public void split(Node node, Node dad, Node grandDad, Node greatGrandDad) {

		if (grandDad != null && dad.m_bIsRed && node.m_bIsRed) {
			// fall 4
			if (grandDad.m_Right != null && grandDad.m_Right.m_Right != null
					&& grandDad.m_Right.m_Right == node) {
				rotateLeft(grandDad, dad, greatGrandDad);
			}
			// fall 5
			if (grandDad.m_Left != null && grandDad.m_Left.m_Left != null
					&& grandDad.m_Left.m_Left == node) {
				rotateRight(grandDad, dad, greatGrandDad);
			}
		}

		if (grandDad != null && dad.m_bIsRed && node.m_bIsRed) {
			// fall 6
			if (grandDad.m_Right != null && grandDad.m_Right.m_Left != null
					&& grandDad.m_Right.m_Left == node) {

				rotateRight(dad, node, grandDad);
				rotateLeft(grandDad, node, greatGrandDad);
			}
			// fall 7
			if (grandDad.m_Left != null && grandDad.m_Left.m_Right != null
					&& grandDad.m_Left.m_Right == node) {
				rotateLeft(dad, node, grandDad);
				rotateRight(grandDad, node, greatGrandDad);
			}
		}
	}

	boolean insert(K key, D data) {
		if (m_Root == null) {
			m_Root = new Node(key, data);
			return true;
		} else {
			Node dad = null;
			Node grandDad = null;
			Node greatGrandDad = null;
			Node node = m_Root;
			// durchlaufen
			while (node != null) {
				if (node.is4Node() == true) {
					if (node == m_Root) {
						m_Root.m_Left.m_bIsRed = false;
						m_Root.m_Right.m_bIsRed = false;
					} else {
						node.convert4Node();
						split(node, dad, grandDad, greatGrandDad);
					}
				}

				// abstieg
				greatGrandDad = grandDad;
				grandDad = dad;
				dad = node;

				if (dad.m_Key.compareTo(key) == 0)
					return false;
				if (dad.m_Key.compareTo(key) < 0) {
					node = node.m_Right;
				} else {
					node = node.m_Left;
				}
			}

			// einf�gen
			Node tmp = new Node(key, data);
			if (dad.m_Key.compareTo(key) < 0) {
				dad.m_Right = tmp;
			} else {
				dad.m_Left = tmp;
			}
			split(tmp, dad, grandDad, greatGrandDad);
			return true;
		}
	}

	// Methode um den Neffen zu bekommen sofern vorhanden
	Node getNephew(Node dad, Node node) {
		if (dad.m_Right == node && dad.m_Left != null
				&& dad.m_Left.m_Right != null)
			return dad.m_Left.m_Right;
		if (dad.m_Left == node && dad.m_Right != null
				&& dad.m_Right.m_Left != null)
			return dad.m_Right.m_Left;
		return null;
	}

	// print bintree methode ausm internet--------------------------------
	public void printBinaryTree() {
		System.out.println("");
		System.out.println("");
		printBinaryTree(m_Root, 0);
		System.out.println("");
		System.out.println("");
	}

	public void printBinaryTree(Node node, int level) {
		if (node == null)
			return;
		printBinaryTree(node.m_Right, level + 1);
		if (level != 0) {
			for (int i = 0; i < level - 1; i++)
				System.out.print("|\t");
			if (node.m_bIsRed == true)
				System.out.println("|--rot-->" + node.m_Key);
			else
				System.out.println("|------->" + node.m_Key);
		} else
			System.out.println(node.m_Key);
		printBinaryTree(node.m_Left, level + 1);
	}

	// treeDept ------------------------------------
	public int treeDept() {
		return treeDept(m_Root, 0);
	}

	private int treeDept(Node node, int maxDept) {
		if (node.m_bIsRed != true)
			maxDept++;

		int maxDeptLeft = 0;
		int maxDeptRight = 0;

		if (node.m_Left != null)
			maxDeptLeft = treeDept(node.m_Left, maxDept);
		if (node.m_Right != null)
			maxDeptRight = treeDept(node.m_Right, maxDept);

		if (maxDeptLeft > maxDept)
			maxDept = maxDeptLeft;
		if (maxDeptRight > maxDept)
			maxDept = maxDeptRight;

		return maxDept;
	}

	// countLeaf ------------------------------------
	public int countLeaf() {
		return countLeaf(m_Root);
	}

	private int countLeaf(Node node) {

		if (node.isLeaf())
			return 1;
		else
			return countLeaf(node.m_Left) + countLeaf(node.m_Right);
	}
}
