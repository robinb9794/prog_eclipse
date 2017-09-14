package klausurStuff.huffman;

import java.io.IOException;

public class TreePrinter {

	Huffman tree = null;
	UDrawGraphClient client = null;
	public boolean ready = false;

	// Construktor
	public TreePrinter(Huffman huffman,boolean console, boolean bat) throws IOException {
		tree = huffman;
		client = new UDrawGraphClient(console,bat);
	}

	// Methoden
	public void printTree(String title) throws IOException {
		print(title);
	}

	// print rb-tree with uDrawGraph
	// --------------------------------------------------
	private void print(String title) throws IOException {
		client.newContext(title);
		print(tree.m_Root);
		client.improve();
	}

	private void print(int node) throws IOException {
		if (tree.m_Left[node] != -1) 
			print(tree.m_Left[node]);
		if (tree.m_Right[node] != -1) 
			print(tree.m_Right[node]);
		
		if (tree.m_Left[node] == -1&&tree.m_Right[node] == -1)
			client.newNode((char)node+"");
		else
			client.newNode(node + "");
		
		if (tree.m_Left[node] != -1) {
			if (tree.m_Left[tree.m_Left[node]] == -1&&tree.m_Right[tree.m_Left[node]] == -1)
				client.newEdgeLeft(node + "", (char)tree.m_Left[node] + "");
			else
				client.newEdgeLeft(node + "", tree.m_Left[node] + "");
		}
		
		if (tree.m_Right[node] != -1) {
			if (tree.m_Left[tree.m_Right[node]] == -1&&tree.m_Right[tree.m_Right[node]] == -1)
				client.newEdgeRight(node + "", (char)tree.m_Right[node] + "");
			else
				client.newEdgeRight(node + "", tree.m_Right[node] + "");
		}
		
		client.improve();
	}

}
