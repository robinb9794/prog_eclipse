package uebung08.aufgabe01;

public class RedBlackTree_noNH_wM<K extends Comparable<K>, D> {

	public class Node {
		
		K m_Key;
		D m_Data;
		Node m_Left = null;
		Node m_Right = null;
		boolean m_bIsRed = true;
		
		public Node(K key, D data) {
			m_Key = key;
			m_Data = data;
			m_bIsRed = true;
		}

		public boolean is2Node() {
			return !m_bIsRed && (m_Left == null || !m_Left.m_bIsRed) && (m_Right == null || !m_Right.m_bIsRed);
		}

		public boolean is4Node() {
			return m_Left != null && m_Left.m_bIsRed && m_Right != null && m_Right.m_bIsRed;
		}

		void convert4Node() {
			m_Left.m_bIsRed = false;
			m_Right.m_bIsRed = false;
			m_bIsRed = true;
		}
	}

	public Node m_Root = null;

	private void rotate(Node node, Node dad, Node gdad) {	
		boolean sonColor = node.m_bIsRed;														// Tausche die Farbe der Kanten
		node.m_bIsRed = dad.m_bIsRed;															// von Vater und Son
		dad.m_bIsRed = sonColor;																//
		
		if(dad.m_Left == node) {																// Befindet sich der Son links unter dem Vater?
			dad.m_Left = node.m_Right;														// Rechter Nachfolger des Sones wird linker Nachfolger des Vaters.
			node.m_Right = dad;																// Vater wird rechter Nachfolger des Sones.
		}
		else {																				// Befindet sich der Son rechts unter dem Vater?
			dad.m_Right = node.m_Left;														// Linker Nachfolger des Sones wird linker Nachfolger des Vaters.
			node.m_Left = dad;																// Vater wird rechter Nachfolger des Sones.
		}
		
		if(gdad == null) {																	// Gibt es einen Großvater?
			m_Root = node;																	// Wenn nicht, überschreibe die Wurzel.
		}
		else if(gdad.m_Left == dad) {														// Befindet sich der Vater links unter dem Großvater?
			gdad.m_Left = node;																// Son wird linker Nachfolger des Großvaters.
		}
		else {																				// Befindet sich der Vater rechts unter dem Großvater?
			gdad.m_Right = node;																// Son wird rechter Nachfolger des Großvaters.
		}
	}

	void rotateLeft(Node node, Node kind, Node dad) {

		node.m_Right = kind.m_Left;
		kind.m_Left = node;

		boolean sonColor = kind.m_bIsRed;
		kind.m_bIsRed = node.m_bIsRed;
		node.m_bIsRed = sonColor;

		if(dad != null) {
			if(dad.m_Right == node)
				dad.m_Right = kind;
			else
				dad.m_Left = kind;
		}
		if(m_Root == node) m_Root = kind;
	}

	void rotateRight(Node node, Node kind, Node dad) {

		node.m_Left = kind.m_Right;
		kind.m_Right = node;

		boolean sonColor = kind.m_bIsRed;
		kind.m_bIsRed = node.m_bIsRed;
		node.m_bIsRed = sonColor;

		if(dad != null) {
			if(dad.m_Right == node)
				dad.m_Right = kind;
			else
				dad.m_Left = kind;
		}
		if(m_Root == node) m_Root = kind;
	}

	// methode um zu erkennen ob 2 rote kanten hinteranderer sind ( alle f�lle)
	// die �bernimmt auch jeghliches rotieren
	public void split(Node node, Node dad, Node grandDad, Node greatGrandDad) {

		if(grandDad != null && dad.m_bIsRed && node.m_bIsRed) {
			// fall 4
			if(grandDad.m_Right != null && grandDad.m_Right.m_Right != null && grandDad.m_Right.m_Right == node) {
				rotateLeft(grandDad, dad, greatGrandDad);
			}
			// fall 5
			if(grandDad.m_Left != null && grandDad.m_Left.m_Left != null && grandDad.m_Left.m_Left == node) {
				rotateRight(grandDad, dad, greatGrandDad);
			}
		}

		if(grandDad != null && dad.m_bIsRed && node.m_bIsRed) {
			// fall 6
			if(grandDad.m_Right != null && grandDad.m_Right.m_Left != null && grandDad.m_Right.m_Left == node) {

				rotateRight(dad, node, grandDad);
				rotateLeft(grandDad, node, greatGrandDad);
			}
			// fall 7
			if(grandDad.m_Left != null && grandDad.m_Left.m_Right != null && grandDad.m_Left.m_Right == node) {
				rotateLeft(dad, node, grandDad);
				rotateRight(grandDad, node, greatGrandDad);
			}
		}
	}

	boolean insert(K key, D data) {
		if(m_Root == null) {
			m_Root = new Node(key, data);
			return true;
		}
		else {
			Node dad = null;
			Node grandDad = null;
			Node greatGrandDad = null;
			Node node = m_Root;
			// durchlaufen
			while(node != null) {
				if(node.is4Node() == true) {
					if(node == m_Root) {
						m_Root.m_Left.m_bIsRed = false;
						m_Root.m_Right.m_bIsRed = false;
					}
					else {
						node.convert4Node();
						split(node, dad, grandDad, greatGrandDad);
					}
				}

				// abstieg
				greatGrandDad = grandDad;
				grandDad = dad;
				dad = node;

				if(dad.m_Key.compareTo(key) == 0) return false;
				if(dad.m_Key.compareTo(key) < 0) {
					node = node.m_Right;
				}
				else {
					node = node.m_Left;
				}
			}

			// einf�gen
			Node tmp = new Node(key, data);
			if(dad.m_Key.compareTo(key) < 0) {
				dad.m_Right = tmp;
			}
			else {
				dad.m_Left = tmp;
			}
			split(tmp, dad, grandDad, greatGrandDad);
			return true;
		}
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
		if(node == null) return;
		printBinaryTree(node.m_Right, level + 1);
		if(level != 0) {
			for(int i = 0; i < level - 1; i++)
				System.out.print("|\t");
			if(node.m_bIsRed == true)
				System.out.println("|--rot-->" + node.m_Key);
			else
				System.out.println("|------->" + node.m_Key);
		}
		else
			System.out.println(node.m_Key);
		printBinaryTree(node.m_Left, level + 1);
	}

}
