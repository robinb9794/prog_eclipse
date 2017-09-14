package uebung12.aufgabe01;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Node {
	static int NodeCount = 0;
	
	public Node() {
		mIdent = NodeCount++;
		mSucc = new LinkedList<>();
		mEdges = new HashMap<>();
		mEdges.put(this, true);
	}
	
	public int getWeight(Node n) {
		if(n == this) return 0;
		for(Pair p : mSucc)
			if(p.getNode() == n) return p.getWeight();
		return Integer.MAX_VALUE;
	}
	
	public int mIdent;
	public LinkedList<Pair> mSucc;
	public Map<Node, Boolean> mEdges;
}