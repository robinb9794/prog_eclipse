package klausurStuff.redBlackTree;

import java.io.IOException;

public class Run {
	public static void main(String[] args) throws IOException {
		RedBlackTree<Integer, Integer> redBlackTree = new RedBlackTree<Integer, Integer>();
		TreePrinter<Integer, Integer> treePrinter = new TreePrinter<Integer, Integer>(
				false, true);

		int[] a = { 5, 22, 36, 3, 2, 48, 19, 8, 15, 27, 50, 30, 31, 7, 34, 49,
				6, 4, 10, 35, 29, 16, 13, 46, 45, 12, 44, 47, 20, 11, 1, 32,
				17, 37, 39, 23, 21, 41, 18, 24, 38, 25, 40, 26, 28, 42, 43 };

		for (int i = 0; i < a.length; i++) {
			redBlackTree.insert(a[i], a[i]);
		}

//		treePrinter.setTree(redBlackTree);
//		treePrinter.printTree("vor remove");

		System.out.println("remove (12): "+redBlackTree.remove(12));
		System.out.println("remove (34): "+redBlackTree.remove(34));
		System.out.println("remove (27): "+redBlackTree.remove(27));
		System.out.println("remove (28): "+redBlackTree.remove(28));
		System.out.println("remove (4): "+redBlackTree.remove(4));
		System.out.println("remove (3): "+redBlackTree.remove(3));
		System.out.println("remove (11): "+redBlackTree.remove(11));
		System.out.println("remove (49): "+redBlackTree.remove(49));
		
		System.out.println(redBlackTree.cnt3Nodes());
		
		treePrinter.setTree(redBlackTree);
		treePrinter.printTree("nach remove");
	}

}
