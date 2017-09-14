package klausurStuff.patriciaTree;

import java.io.IOException;

// Aufgabe 1: 
// Implementieren Sie Patrica Trees ohne Verwendung der NodeHandler
// Klasse, in denen Sie zu Strings beliebige Werte (Generics verwenden) 
// abspeichern können. 

public class Run {
	public static String getString(int len) {
		java.util.Random rnd = new java.util.Random();
		String s = new String();
		for(int j = 0; j < len; ++j)
			s += (char) (rnd.nextInt(26) + 65);
		return s;
	}

	Run() throws IOException {
		TreePrinter printer = new TreePrinter(false, true);
		PatriciaTree_noNH_wM patriciaTree_noNH_wM = new PatriciaTree_noNH_wM();
		
		char[] M = {1,2,3,4,5};
		
		for(int i=0; i<M.length; ++i) {
			patriciaTree_noNH_wM.insert(M[i]);
		}

//		for(int i = 65; i < 91; ++i) {
//			System.out.println("insert " + i + " (" + i + ") : " + patriciaTree_noNH_wM.insert((char) i));
//		}
//
//		for(int i = 97; i < 123; ++i) {
//			System.out.println("insert " + i + " (" + i + ") : " + patriciaTree_noNH_wM.insert((char) i));
//		}
		
//		System.out.println(patriciaTree_noNH_wM.searchRec('D'));
//		System.out.println(patriciaTree_noNH_wM.search('A'));
//		System.out.println("MaxDepth: " + patriciaTree_noNH_wM.maxDepth());
//		System.out.println("MinDepth: " + patriciaTree_noNH_wM.minDepth());
//		System.out.println("BackLinks: " + patriciaTree_noNH_wM.countBackLinks());
//		System.out.println("SelfLinks: " + patriciaTree_noNH_wM.countSelfLinks());
//		System.out.println("NullNode: " + patriciaTree_noNH_wM.getNullNode());
//		System.out.println("GoldNode: " + patriciaTree_noNH_wM.getGoldNode());
//		System.out.println("NodeCount: " + patriciaTree_noNH_wM.countNodes());
//
//		Vector<Character> vec = patriciaTree_noNH_wM.convertToVector();
//		System.out.println(vec);

		printer.setTree(patriciaTree_noNH_wM);
		printer.printTree("patriciaTree_noNH_wM");

	}

	public static void main(String[] args) throws IOException {
		new Run();
	}
}
