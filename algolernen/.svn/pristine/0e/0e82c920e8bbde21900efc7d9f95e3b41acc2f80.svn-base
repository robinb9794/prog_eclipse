package uebung08.aufgabe02;

import java.io.IOException;

import uebung08.aufgabe02.RedBlackTree_noNH_wM.Node;

public class TreePrinter<K extends Comparable<K>, D>{

	RedBlackTree_noNH_wM tree = null;
	UDrawGraphClient client = null;
	public boolean ready = false;

	// Construktor
	public TreePrinter(boolean console, boolean bat) throws IOException {
		client = new UDrawGraphClient(console,bat);
	}

	public void setTree(RedBlackTree_noNH_wM tree){
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
		print(tree.m_Root, tree.m_Root);
		ready=true;
	}

	private void print(Node node, Node vater) throws IOException {
		client.newNode(node);
		if (node != tree.m_Root) {
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
