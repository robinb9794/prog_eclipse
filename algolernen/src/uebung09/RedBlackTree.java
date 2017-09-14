package uebung09;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class RedBlackTree<K extends Comparable<K>, D> {
	
	public Node<K, D> m_Root;
	
	public RedBlackTree() {
		m_Root = null;
	}

	public Node<K, D> search(K key) {
		Node<K, D> tmp = m_Root;
		while(tmp != null) {
			final int RES = tmp.m_Key.compareTo(key);
			if(RES == 0)
				return tmp;
			tmp = RES > 0 ? tmp.m_Left : tmp.m_Right;
		}
		return null;
	}

	public boolean insert(K key, D data) {
		if(m_Root == null) {
			m_Root = new Node<K, D>(key, data);
		}
		else {
			Node<K, D> ggdad = null;
			Node<K, D> gdad = null;
			Node<K, D> dad = null;
			Node<K, D> node = m_Root;
			int res = 0;
			while(node != null) {
				if(node.isFourNode()) {
					node.convertFourNode();
					split(node, dad, gdad, ggdad);
					// if(gdad != null) node = gdad;
				}
				ggdad = gdad;
				gdad = dad;
				dad = node;
				res = node.m_Key.compareTo(key);
				if(res == 0) {
					m_Root.m_bIsRed = false;
					return false;
				}
				else if(res > 0)
					node = node.m_Left;
				else
					node = node.m_Right;
			}
			node = new Node<K, D>(key, data);
			if(res > 0)
				dad.m_Left = node;
			else
				dad.m_Right = node;

			split(node, dad, gdad, ggdad);
		}
		m_Root.m_bIsRed = false;
		return true;
	}

	private void split(Node<K, D> node, Node<K, D> dad, Node<K, D> gdad, Node<K, D> ggdad) {
		if(gdad != null && node.m_bIsRed && dad.m_bIsRed) { // Gibt es hintereinander zwei rote Kanten?
			if((gdad.m_Key.compareTo(dad.m_Key) < 0) == (dad.m_Key.compareTo(node.m_Key) < 0)) { // Sind die Kanten gleich ausgerichtet?
				rotate(dad, gdad, ggdad); // Rotiere
			}
			else { // Sind die Kanten nicht gleich ausgerichtet? BLITZ!
				rotate(node, dad, gdad); // Rotiere
				rotate(node, gdad, ggdad); // Rotiere
			}
		}
	}

	private void rotate(Node<K, D> node, Node<K, D> dad, Node<K, D> gdad) {
		boolean sonColor = node.m_bIsRed; // Tausche die Farbe der Kanten
		node.m_bIsRed = dad.m_bIsRed; // von Vater und Son
		dad.m_bIsRed = sonColor;
		if(dad.m_Left == node) { // Befindet sich der Son links unter dem Vater?
			dad.m_Left = node.m_Right; // Rechter Nachfolger des Sones wird linker Nachfolger des Vaters.
			node.m_Right = dad; // Vater wird rechter Nachfolger des Sones.
		}
		else { // Befindet sich der Son rechts unter dem Vater?
			dad.m_Right = node.m_Left; // Linker Nachfolger des Sones wird linker Nachfolger des Vaters.
			node.m_Left = dad; // Vater wird rechter Nachfolger des Sones.
		}
		if(gdad == null) // Gibt es einen Großvater?
			m_Root = node; // Wenn nicht, überschreibe die Wurzel.
		else if(gdad.m_Left == dad) // Befindet sich der Vater links unter dem Großvater?
			gdad.m_Left = node; // Son wird linker Nachfolger des Großvaters.
		else
			// Befindet sich der Vater rechts unter dem Großvater?
			gdad.m_Right = node; // Son wird rechter Nachfolger des Großvaters.
	}

	private void join(Node<K, D> node, Node<K, D> dad, Node<K, D> gdad) {
		if(node.isTwoNode() && dad != null) { // FALL 1-9
			boolean left = dad.m_Left == node;
			Node<K, D> brother = left ? dad.m_Right : dad.m_Left;
			if(!dad.m_bIsRed && brother.m_bIsRed) { // FALL 6-9
				rotate(brother, dad, gdad);
				gdad = brother;
				brother = left ? dad.m_Right : dad.m_Left;
			}
			if(((dad.m_bIsRed || dad == m_Root)) && !brother.m_bIsRed) { // FALL 1-5
				Node<K, D> nephew = left ? brother.m_Left : brother.m_Right;
				if(nephew != null && nephew.m_bIsRed) { // FALL 4-5
					rotate(nephew, brother, dad);
					brother = nephew;
				}
				nephew = left ? brother.m_Right : brother.m_Left; // FALL 3
				if(nephew != null && nephew.m_bIsRed) {
					rotate(brother, dad, gdad);
					node.m_bIsRed = true;
					nephew.m_bIsRed = false;
				}
				else { // FALL 1-2
					dad.m_bIsRed = false;
					brother.m_bIsRed = true;
					node.m_bIsRed = true;
				}
			}
		}
	}

	public boolean remove(K key) {
		if(m_Root == null)
			return false;
		Node<K, D> gdad = null;
		Node<K, D> dad = null;
		Node<K, D> node = m_Root;
		while(node != null) {
			join(node, dad, gdad);
			gdad = dad;
			dad = node;
			final int RES = node.m_Key.compareTo(key);
			if(RES > 0)
				node = node.m_Left;
			else if(RES < 0)
				node = node.m_Right;
			else {
				if(node.m_Left == null || node.m_Right == null) {
					Node<K, D> son = node.m_Right == null ? node.m_Left : node.m_Right;
					if(son != null)
						son.m_bIsRed = node.m_bIsRed;
					if(gdad == null)
						m_Root = son;
					else if(gdad.m_Left == node)
						gdad.m_Left = son;
					else
						gdad.m_Right = son;
				}
				else {
					Node<K, D> toDel = node;
					node = node.m_Right;
					while(node != null) {
						join(node, dad, gdad);
						gdad = dad;
						dad = node;
						node = node.m_Left;
					}
					toDel.m_Key = dad.m_Key;
					toDel.m_Data = dad.m_Data;
					if(dad.m_Right != null)
						dad.m_Right.m_bIsRed = dad.m_bIsRed;
					if(gdad == toDel)
						gdad.m_Right = dad.m_Right;
					else
						gdad.m_Left = dad.m_Right;
				}
				return true;
			}
		}
		return true;
	}

	public boolean isEmpty() {
		return m_Root == null;
	}

	public void print(String fileName) {
		try {
			File treeFile = new File(fileName + ".udg");
			PrintWriter pWriter = new PrintWriter(new FileWriter(treeFile));
			pWriter.println("[");
			printTree(m_Root, pWriter);
			pWriter.println("]");
			pWriter.flush();
			pWriter.close();
			java.awt.Desktop.getDesktop().open(treeFile);
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}

	private void printTree(Node<K, D> node, PrintWriter writer) {
		if(node != null) {
			printTree(node.m_Left, writer);
			writer.println(node.printNode());
			printTree(node.m_Right, writer);
		}
	}
}