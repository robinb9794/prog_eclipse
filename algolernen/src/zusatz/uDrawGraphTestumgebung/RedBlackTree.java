package zusatz.uDrawGraphTestumgebung;

public class RedBlackTree<K extends Comparable<K>, D> implements Tree {

	public Node<K, D> m_Root = null;

	// Node - Handler ------------------------------------------------------
	public class NodeHandler {
		public final int NODE = 0;
		public final int DAD = 1;
		public final int G_DAD = 2;
		public final int GG_DAD = 3;
		private Object[] m_Nodes = new Object[4];

		NodeHandler(Node<K, D> n) {
			m_Nodes[NODE] = n;
		}

		NodeHandler(NodeHandler h) {
			m_Nodes[NODE] = h.m_Nodes[NODE];
			m_Nodes[DAD] = h.m_Nodes[DAD];
			m_Nodes[G_DAD] = h.m_Nodes[G_DAD];
			m_Nodes[GG_DAD] = h.m_Nodes[GG_DAD];
		}

		void down(boolean left) {
			for (int i = m_Nodes.length - 1; i > 0; --i)
				m_Nodes[i] = m_Nodes[i - 1];
			m_Nodes[NODE] = left ? node(DAD).m_Left : node(DAD).m_Right;
		}

		boolean isNull() {
			return m_Nodes[NODE] == null;
		}

		private void split() {
			Node<K, D> dad = node(DAD);
			if (dad != null && dad.m_bIsRed) {
				if (node(G_DAD).m_Key.compareTo(dad.m_Key) < 0 != dad.m_Key
						.compareTo(node(NODE).m_Key) < 0)
					rotate(DAD);
				rotate(G_DAD);
			}
		}

		void rotate(int kind) {
			Node<K, D> dad = node(kind);
			Node<K, D> son = node(kind - 1);
			boolean sonColour = son.m_bIsRed;
			if (!sonColour) {
				if (son.m_Left != null)
					son.m_Left.m_bIsRed = false;
				if (son.m_Right != null)
					son.m_Right.m_bIsRed = false;
				dad.m_bIsRed = false;
				dad.m_Left.m_bIsRed = true;
				dad.m_Right.m_bIsRed = true;
			} else {
				son.m_bIsRed = dad.m_bIsRed;
				dad.m_bIsRed = sonColour;
			}
			// rotate
			if (dad.m_Left == son) {
				// clockwise rotation
				dad.m_Left = son.m_Right;
				son.m_Right = dad;
			} else {
				// counter-clockwise rotation
				dad.m_Right = son.m_Left;
				son.m_Left = dad;
			}
			set(son, kind, false);
		}

		void set(Node<K, D> n, int kind, boolean copyColours) {
			if (node(kind + 1) == null)
				m_Root = n;
			else if (node(kind) != null ? node(kind + 1).m_Left == node(kind)
					: n.m_Key.compareTo(node(kind + 1).m_Key) < 0)
				node(kind + 1).m_Left = n;
			else
				node(kind + 1).m_Right = n;
			if (copyColours && node(kind) != null && n != null)
				n.m_bIsRed = node(kind).m_bIsRed;
			m_Nodes[kind] = n;
		}

		private void join() {
			if (node(NODE).is2Node()) {
				if (node(DAD) == null && node(NODE).m_Left != null
						&& node(NODE).m_Left.is2Node()
						&& node(NODE).m_Right != null
						&& node(NODE).m_Right.is2Node()) {
					node(NODE).m_Left.m_bIsRed = true;
					node(NODE).m_Right.m_bIsRed = true;
				} else if (node(DAD) != null) {
					NodeHandler nephew = getNephew();
					if (nephew.node(DAD).m_bIsRed) {
						nephew.rotate(G_DAD);
						m_Nodes[GG_DAD] = m_Nodes[G_DAD];
						m_Nodes[G_DAD] = nephew.m_Nodes[G_DAD];
						nephew = getNephew();
					}
					if (nephew.node(DAD).is2Node()) {
						node(NODE).m_bIsRed = true;
						nephew.node(DAD).m_bIsRed = true;
						node(DAD).m_bIsRed = false;
					} else {
						if (!nephew.isNull() && nephew.node(NODE).m_bIsRed)
							nephew.rotate(DAD);
						nephew.rotate(G_DAD);
					}
				}
			}
		}

		NodeHandler getNephew() {
			Node<K, D> node = node(NODE);
			Node<K, D> dad = node(DAD);
			Node<K, D> gDad = node(G_DAD);

			Node<K, D> brother = node == dad.m_Left ? dad.m_Right : dad.m_Left;
			Node<K, D> nephew = node == dad.m_Left ? brother.m_Left
					: brother.m_Right;
			NodeHandler res = new NodeHandler(nephew);

			res.m_Nodes[DAD] = brother;
			res.m_Nodes[G_DAD] = dad;
			res.m_Nodes[GG_DAD] = gDad;
			return res;
		}

		Node<K, D> node(int kind) {
			return (Node<K, D>) m_Nodes[kind];
		}
	}

	// Red - Black - Tree -
	// Methoden-----------------------------------------------
	boolean insert(K key, D data) {
		NodeHandler h = new NodeHandler(m_Root);
		while (!h.isNull()) {
			if (h.node(h.NODE).is4Node()) {
				h.node(h.NODE).convert4Node();
				h.split();
			}
			final int RES = key.compareTo(h.node(h.NODE).m_Key);
			if (RES == 0)
				return false;
			h.down(RES < 0);
		}
		h.set(new Node<K, D>(key, data), h.NODE, true);
		h.split();
		m_Root.m_bIsRed = false;
		return true;
	}

	boolean remove(K key) {
		NodeHandler h = new NodeHandler(m_Root);
		while (!h.isNull()) {
			h.join();
			final int RES = key.compareTo(h.node(h.NODE).m_Key);
			if (RES == 0) {
				if (h.node(h.NODE).m_Right == null) {
					h.set(h.node(h.NODE).m_Left, h.NODE, true);
				} else {
					NodeHandler h2 = new NodeHandler(h);
					h2.down(false); // go right
					h2.join();
					while (h2.node(h2.NODE).m_Left != null) {
						h2.down(true);
						h2.join();
					}
					h.node(h.NODE).m_Key = h2.node(h2.NODE).m_Key;
					h.node(h.NODE).m_Data = h2.node(h2.NODE).m_Data;
					h2.set(h2.node(h2.NODE).m_Right, h2.NODE, true);
				}
				if (m_Root != null)
					m_Root.m_bIsRed = false;
				return true;
			}
			h.down(RES < 0);
		}
		return false;
	}

	public Node<K, D> search(K key) {
		Node<K, D> tmp = m_Root;
		while (tmp != null) {
			final int RES = key.compareTo(tmp.m_Key);
			if (RES == 0)
				return tmp;
			tmp = RES < 0 ? tmp.m_Left : tmp.m_Right;
		}
		return null;
	}

	@Override
	public Node<K, D> getRoot() {
		return m_Root;
	}
}