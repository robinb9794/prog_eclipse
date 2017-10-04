package uebung10.aufgabe01;

public class PatriciaTree_noNH_wM<D> {

	Node m_Root;

	public class Node {
		String m_Key;
		String binString;
		D data;
		Node m_Left;
		Node m_Right;
		int m_BitPos;

		public Node(String key,D data, int bitPos) {
			this(key, bitPos,data, null);
		}

		public Node(String key, int bitPos,D data, Node succ) {
			m_Key = key;
			this.data=data;
			binString = stringToBinaryString(key);
			m_BitPos = bitPos;
			boolean left = left(binString, bitPos);
			m_Left = left ? this : succ;
			m_Right = left ? succ : this;
		}

		private String stringToBinaryString(String string) {
			String temp = "";
			for (int i = 0; i < string.length(); ++i) {
				String bin = "";
				for (int j = 31; j >= 0; j--)
					bin = bin + ((string.charAt(i) >> j & 1) == 1 ? "1" : "0");
				temp = temp + bin;
			}
			return temp;
		}

		@Override
		public String toString() {
			return "Bit: " + m_BitPos + " Key: " + m_Key+" Data: "+data;
		}
	}

	public PatriciaTree_noNH_wM() {
		m_Root = null;
	}

	private boolean left(String key, int bitPos) {
		int cPos = bitPos / 8;
		char c = key.length() > cPos ? key.charAt(cPos) : '\0';
		return (c & (1 << bitPos % 8)) == 0;
	}

	private String stringToBinaryString(String string) {
		String temp = "";
		for (int i = 0; i < string.length(); ++i) {
			String bin = "";
			for (int j = 31; j >= 0; j--)
				bin = bin + ((string.charAt(i) >> j & 1) == 1 ? "1" : "0");
			temp = temp + bin;
		}
		return temp;
	}

	public boolean insert(String c,D data) {
		if (m_Root == null) {
			m_Root = new Node(c,data, 0);
			return true;
		}

		String binString = stringToBinaryString(c);

		int lastBitPos = -1;
		Node node = m_Root;
		Node dad = null;

		while (node != null && lastBitPos < node.m_BitPos) {
			lastBitPos = node.m_BitPos;
			dad = node;
			node = left(c, node.m_BitPos) ? node.m_Left : node.m_Right;
		}

		int newBitPos = 0;
		if (node == null) {
			newBitPos = dad.m_BitPos + 1;
		} else if (node.m_Key == c) {
			return false;
		} else {
			while (left(c, newBitPos) == left(node.m_Key, newBitPos))
				++newBitPos;

			lastBitPos = -1;
			while (node != null && lastBitPos < node.m_BitPos
					&& node.m_BitPos < newBitPos) {
				lastBitPos = node.m_BitPos;
				dad = node;
				node = left(c, node.m_BitPos) ? node.m_Left : node.m_Right;
			}
		}

		Node newNode = new Node(c, newBitPos,data, node);
		if (left(binString, dad.m_BitPos))
			dad.m_Left = newNode;
		else
			dad.m_Right = newNode;
		return true;
	}
}