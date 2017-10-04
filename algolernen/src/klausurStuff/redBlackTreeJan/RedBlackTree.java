package klausurStuff.redBlackTreeJan;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

public class RedBlackTree<K extends Comparable<K>, D> {
	public RedBlackTree() {
		mRoot = null;
	}

	public Node<K, D> searchRec(K key) {
		return searchRec(mRoot, key);
	}

	private Node<K, D> searchRec(Node<K, D> node, K key) {
		if(node != null) {
			final int RES = node.mKey.compareTo(key);
			if(RES == 0)
				return node;
			else if(RES > 0)
				return searchRec(node.mLeft, key);
			else
				return searchRec(node.mRight, key);
		}
		return null;
	}

	public Node<K, D> search(K key) {
		Node<K, D> tmp = mRoot;
		while(tmp != null) {
			final int RES = tmp.mKey.compareTo(key);
			if(RES == 0)
				return tmp;
			tmp = RES > 0 ? tmp.mLeft : tmp.mRight;
		}
		return null;
	}

	public boolean insert(K key, D data) {
		if(mRoot == null) {
			mRoot = new Node<K, D>(key, data);
		}
		else {
			Node<K, D> ggdad = null;
			Node<K, D> gdad = null;
			Node<K, D> dad = null;
			Node<K, D> node = mRoot;
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
				res = node.mKey.compareTo(key);
				if(res == 0) {
					mRoot.mIsRed = false;
					return false;
				}
				else if(res > 0)
					node = node.mLeft;
				else
					node = node.mRight;
			}
			node = new Node<K, D>(key, data);
			if(res > 0)
				dad.mLeft = node;
			else
				dad.mRight = node;

			split(node, dad, gdad, ggdad);
		}
		mRoot.mIsRed = false;
		return true;
	}

	private void split(Node<K, D> node, Node<K, D> dad, Node<K, D> gdad, Node<K, D> ggdad) {
		if(gdad != null && node.mIsRed && dad.mIsRed) { // Gibt es hintereinander zwei rote Kanten?
			if((gdad.mKey.compareTo(dad.mKey) < 0) == (dad.mKey.compareTo(node.mKey) < 0)) { // Sind die Kanten gleich ausgerichtet?
				rotate(dad, gdad, ggdad); // Rotiere
			}
			else { // Sind die Kanten nicht gleich ausgerichtet? BLITZ!
				rotate(node, dad, gdad); // Rotiere
				rotate(node, gdad, ggdad); // Rotiere
			}
		}
	}

	private void rotate(Node<K, D> node, Node<K, D> dad, Node<K, D> gdad) {
		boolean sonColor = node.mIsRed; // Tausche die Farbe der Kanten
		node.mIsRed = dad.mIsRed; // von Vater und Son
		dad.mIsRed = sonColor;
		if(dad.mLeft == node) { // Befindet sich der Son links unter dem Vater?
			dad.mLeft = node.mRight; // Rechter Nachfolger des Sones wird linker Nachfolger des Vaters.
			node.mRight = dad; // Vater wird rechter Nachfolger des Sones.
		}
		else { // Befindet sich der Son rechts unter dem Vater?
			dad.mRight = node.mLeft; // Linker Nachfolger des Sones wird linker Nachfolger des Vaters.
			node.mLeft = dad; // Vater wird rechter Nachfolger des Sones.
		}
		if(gdad == null) // Gibt es einen Großvater?
			mRoot = node; // Wenn nicht, überschreibe die Wurzel.
		else if(gdad.mLeft == dad) // Befindet sich der Vater links unter dem Großvater?
			gdad.mLeft = node; // Son wird linker Nachfolger des Großvaters.
		else
			// Befindet sich der Vater rechts unter dem Großvater?
			gdad.mRight = node; // Son wird rechter Nachfolger des Großvaters.
	}

	private void join(Node<K, D> node, Node<K, D> dad, Node<K, D> gdad) {
		if(node.isTwoNode() && dad != null) { // FALL 1-9
			boolean left = dad.mLeft == node;
			Node<K, D> brother = left ? dad.mRight : dad.mLeft;
			if(!dad.mIsRed && brother.mIsRed) { // FALL 6-9
				rotate(brother, dad, gdad);
				gdad = brother;
				brother = left ? dad.mRight : dad.mLeft;
			}
			if(((dad.mIsRed || dad == mRoot)) && !brother.mIsRed) { // FALL 1-5
				Node<K, D> nephew = left ? brother.mLeft : brother.mRight;
				if(nephew != null && nephew.mIsRed) { // FALL 4-5
					rotate(nephew, brother, dad);
					brother = nephew;
				}
				nephew = left ? brother.mRight : brother.mLeft; // FALL 3
				if(nephew != null && nephew.mIsRed) {
					rotate(brother, dad, gdad);
					node.mIsRed = true;
					nephew.mIsRed = false;
				}
				else { // FALL 1-2
					dad.mIsRed = false;
					brother.mIsRed = true;
					node.mIsRed = true;
				}
			}
		}
	}

	public boolean remove(K key) {
		if(mRoot == null)
			return false;
		Node<K, D> gdad = null;
		Node<K, D> dad = null;
		Node<K, D> node = mRoot;
		while(node != null) {
			join(node, dad, gdad);
			gdad = dad;
			dad = node;
			final int RES = node.mKey.compareTo(key);
			if(RES > 0)
				node = node.mLeft;
			else if(RES < 0)
				node = node.mRight;
			else {
				if(node.mLeft == null || node.mRight == null) {
					Node<K, D> son = node.mRight == null ? node.mLeft : node.mRight;
					if(son != null)
						son.mIsRed = node.mIsRed;
					if(gdad == null)
						mRoot = son;
					else if(gdad.mLeft == node)
						gdad.mLeft = son;
					else
						gdad.mRight = son;
				}
				else {
					Node<K, D> toDel = node;
					node = node.mRight;
					while(node != null) {
						join(node, dad, gdad);
						gdad = dad;
						dad = node;
						node = node.mLeft;
					}
					toDel.mKey = dad.mKey;
					toDel.mData = dad.mData;
					if(dad.mRight != null)
						dad.mRight.mIsRed = dad.mIsRed;
					if(gdad == toDel)
						gdad.mRight = dad.mRight;
					else
						gdad.mLeft = dad.mRight;
				}
				return true;
			}
		}
		return true;
	}

	public boolean isEmpty() {
		return mRoot == null;
	}

	public int maxDepth() {
		return depth(mRoot, true);
	}

	public int minDepth() {
		return depth(mRoot, false);
	}

	private int depth(Node<K, D> node, boolean max) {
		if(node == null)
			return 0;
		return max ? Math.max(depth(node.mLeft, max), depth(node.mRight, max)) + (node.mIsRed ? 0 : 1) : Math.min(depth(node.mLeft, max), depth(node.mRight, max)) + (node.mIsRed ? 0 : 1);
	}

	public int countLeaf() {
		return countLeaf(mRoot);
	}

	private int countLeaf(Node<K, D> n) {
		if(n.isLeaf())
			return 1;
		else
			return countLeaf(n.mLeft) + countLeaf(n.mRight);
	}

	public int countThreeNodes() {
		return countThreeNodes(mRoot);
	}

	private int countThreeNodes(Node<K, D> n) {
		if(n == null)
			return 0;
		else
			return countThreeNodes(n.mLeft) + countThreeNodes(n.mRight) + (n.isThreeNode() ? 1 : 0);
	}

	public int countFourNodes() {
		return countFourNodes(mRoot);
	}

	private int countFourNodes(Node<K, D> n) {
		if(n == null)
			return 0;
		else
			return countFourNodes(n.mLeft) + countFourNodes(n.mRight) + (n.isFourNode() ? 1 : 0);
	}

	public Vector<K> toKeyVector() {
		Vector<K> vRet = new Vector<>();
		toKeyVector(mRoot, vRet);
		return vRet;
	}

	private void toKeyVector(Node<K, D> n, Vector<K> v) {
		if(n != null) {
			toKeyVector(n.mLeft, v);
			v.add(n.mKey);
			toKeyVector(n.mRight, v);
		}
	}

	public Vector<D> toDataVector() {
		Vector<D> vRet = new Vector<>();
		toDataVector(mRoot, vRet);
		return vRet;
	}

	private void toDataVector(Node<K, D> n, Vector<D> v) {
		if(n != null) {
			toDataVector(n.mLeft, v);
			v.add(n.mData);
			toDataVector(n.mRight, v);
		}
	}

	public int countRedEdges() {
		return countEdges(mRoot, true);
	}

	public int countBlackEdges() {
		return countEdges(mRoot, false);
	}

	private int countEdges(Node<K, D> n, boolean red) {
		if(n == null)
			return 0;
		if(red)
			return countEdges(n.mLeft, red) + countEdges(n.mRight, red) + (n.mIsRed ? 1 : 0);
		else
			return countEdges(n.mLeft, red) + countEdges(n.mRight, red) + (n.mIsRed ? 0 : 1);
	}

	public boolean isSorted() {
		return isSorted(mRoot);
	}

	private boolean isSorted(Node<K, D> n) {
		if(n == null)
			return true;
		else
			return n.isSorted() && isSorted(n.mLeft) && isSorted(n.mRight);
	}

	public boolean checkDoubleRed() {
		return checkDoubleRed(mRoot);
	}

	private boolean checkDoubleRed(Node<K, D> n) {
		return n == null ? false : n.isDoubleRed() && checkDoubleRed(n.mLeft) && checkDoubleRed(n.mRight);
	}

	public boolean isValid() {
		return isSorted() && !checkDoubleRed();
	}

	public void print(String fileName) {
		try {
			File treeFile = new File(fileName + ".udg");
			PrintWriter pWriter = new PrintWriter(new FileWriter(treeFile));
			pWriter.println("[");
			printTree(mRoot, pWriter);
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
			printTree(node.mLeft, writer);
			writer.println(node.printNode());
			printTree(node.mRight, writer);
		}
	}

	private Node<K, D> mRoot;
}