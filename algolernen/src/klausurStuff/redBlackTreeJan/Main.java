package klausurStuff.redBlackTreeJan;

public class Main {
	public static String getString(int len)	{
    	java.util.Random rnd = new java.util.Random();
    	String s = new String();
    	for(int j=0; j<len; ++j)	
    		s += (char)(rnd.nextInt(26)+65);
   		return s; 	
	}
	
	public static void main(String[] args) {
		int[] M = {3,-1,10,200,-180,170,4,5,11,20,43,-8,-4,-10,15,17};
		
		RedBlackTree<Integer, String> tree = new RedBlackTree<Integer, String>();
	
		for(int i=0; i<M.length; ++i) {
			tree.insert(M[i], getString(5));
		}
		
//		System.out.println(tree.searchRec(71717));
		System.out.println("ThreeNodes: " + tree.countThreeNodes());
		System.out.println("FourNodes: " + tree.countFourNodes());
		System.out.println("MinDepth: " + tree.minDepth());
		System.out.println("MaxDepth: " + tree.maxDepth());
		System.out.println("LeafCount: " + tree.countLeaf());
		System.out.println("BlackEdges: " + tree.countBlackEdges());
		System.out.println("RedEdges: " + tree.countRedEdges());
		System.out.println("checkDoubleRed: " + tree.checkDoubleRed());
		System.out.println("isSorted: " + tree.isSorted());
		System.out.println("isValid: " + tree.isValid());
		
//		tree.remove(13);
		
		tree.print("RedBlackTree");
	}
}
