package zusatz.uDrawGraphTestumgebung;

import java.io.IOException;

public class TreePrinter<K extends Comparable<K>, D>{

	Tree<K, D> tree = null;
	UDrawGraphClient client = null;
	public boolean ready = false;

	// Construktor
	public TreePrinter(boolean console,boolean bat) throws IOException {
		client = new UDrawGraphClient(console,bat);
	}

	public void setTree(Tree<K, D> tree){
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
		if (tree.getRoot() == null)
			return;
		print(tree.getRoot(), tree.getRoot());
		ready=true;
	}

	private void print(Node<K, D> node, Node<K, D> vater) throws IOException {
		client.newNode(node);
		if (node != tree.getRoot()) {
			if (node.m_bIsRed == true) {
				if (vater.m_Left != null && vater.m_Left == node) {
					client.newRedEdgeLeft(vater, node);
				}
				if (vater.m_Right != null && vater.m_Right == node) {
					client.newRedEdgeRight(vater, node);
				}
			} else {
				if (vater.m_Left != null && vater.m_Left == node) {
					client.newEdgeLeft(vater, node);
				}
				if (vater.m_Right != null && vater.m_Right == node) {
					client.newEdgeRight(vater, node);
				}
			}
		}
		if (node.m_Left != null) {
			print(node.m_Left, node);
		}
		if (node.m_Right != null) {
			print(node.m_Right, node);
		}
		if (node.m_Left == null && node.m_Right == null)
			client.improve();
	}
}
