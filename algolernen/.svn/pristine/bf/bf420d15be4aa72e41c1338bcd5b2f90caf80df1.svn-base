package klausurStuff.top234;

import java.io.IOException;

public class TreePrinter<K extends Comparable<K>, D>{

	Top234Tree tree = null;
	UDrawGraphClient client = null;
	public boolean ready = false;

	// Construktor
	public TreePrinter(boolean console, boolean bat) throws IOException {
		client = new UDrawGraphClient(console,bat);
	}

	public void setTree(Top234Tree tree){
		this.tree = tree;
	}
	
	// Methoden
	public void printTree(String title) throws IOException {
		print(title);
	}

	// print rb-tree with uDrawGraph
	// --------------------------------------------------
	private void print(String title) throws IOException {
		ready = false;
		client.newContext(title);
		if (tree.m_Root == null)
			return;
		print(tree.m_Root);
		client.improve();
		ready=true;
	}

	private void print(Node node) throws IOException {

		client.newNode(node);
		
		int numItems =node.getNumItems();
		for (int j = 0; j < numItems + 1; j++) {
			Node nextNode = node.getChild(j);
			if (nextNode != null){
				print(nextNode);
				client.newEdge(node, nextNode);
			}
			else{
				client.improve();
				return;
			}
		}
	}
}
