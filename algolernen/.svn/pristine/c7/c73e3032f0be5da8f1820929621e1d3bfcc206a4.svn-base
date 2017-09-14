package uebung12.aufgabe03;

import java.util.Hashtable;
import java.util.LinkedList;

public class GraphList {
	LinkedList<Node> mRoots;
	Hashtable<String, Node> mRootNames;
	Hashtable<Node, String> mRevRootNames;
	final boolean IS_BI_DIRECTED;

	public GraphList(boolean isBiDirected) {
		mRoots = new LinkedList<>();
		mRootNames = new Hashtable<>();
		mRevRootNames = new Hashtable<>();
		IS_BI_DIRECTED = isBiDirected;
	}

	public Node newNode(String nodeName) {
		Node newNode = mRootNames.get(nodeName);
		if(newNode == null) {
			newNode = new Node();
			mRoots.add(newNode);
			mRootNames.put(nodeName, newNode);
			mRevRootNames.put(newNode, nodeName);
		}
		return newNode;
	}

	public void addEdge(String from, String to, Integer weight) {
		Node f = mRootNames.get(from);
		Node t = mRootNames.get(to);
		if(f != null && t != null) {
			if(!f.mEdges.containsKey(t)) {
				f.mSucc.add(new Pair(t, weight));
				f.mEdges.put(t, true);
			}
			if(IS_BI_DIRECTED) {
				if(!t.mEdges.containsKey(f)) {
					t.mSucc.add(new Pair(f, weight));
					t.mEdges.put(f, true);
				}
			}
		}
	}

	public void getMySpanBaumBitch(String node) {
		PrioHeap ph = new PrioHeap(mRoots.size());
		boolean[] visited = new boolean[mRoots.size()];
		for(int i = 0; i < mRoots.size(); ++i)
			visited[i] = false;
		ph.insert(mRootNames.get(node).mIdent, 0.0f);
		while(!ph.isEmpty()) {
			float[] fDistance = new float[1];
			int iNextNode = ph.remove(fDistance);
			visited[iNextNode] = true;
			System.out.println(mRevRootNames.get(mRoots.get(iNextNode)) + " with distance " + fDistance[0]);
			for(int i = 0; i < mRoots.get(iNextNode).mSucc.size(); ++i) {
				float NEW_DISTANCE = mRoots.get(iNextNode).mSucc.get(i).getWeight();
				if(!visited[mRoots.get(iNextNode).mSucc.get(i).getNode().mIdent]) {
					ph.insert(mRoots.get(iNextNode).mSucc.get(i).getNode().mIdent, NEW_DISTANCE + fDistance[0]);
				}
			}
		}
	}
	
	public int maxConnectedComponent() {
		boolean[] visited = new boolean[mRoots.size()];
		int iComp = 0;
		for(int i = 0; i < visited.length; ++i) {
			if(!visited[i]) {
				++iComp;
				depthSearch(mRoots.get(i), visited);
			}
		}
		return iComp;
	}
	
	public void depthSearch(String node) {
		Node n = mRootNames.get(node);
		boolean[] visited = new boolean[mRoots.size()];
		visited[n.mIdent] = true;
		depthSearch(n, visited);
	}
	
	private void depthSearch(Node node, boolean[] visited) {
		for(int i = 0; i < node.mSucc.size(); ++i) {
			if(!visited[node.mSucc.get(i).getNode().mIdent]) {
				visited[node.mSucc.get(i).getNode().mIdent] = true;
				depthSearch(node.mSucc.get(i).getNode(), visited);
			}
		}
	}
	
	public boolean isReachable(String from, String to) {
		Node f = mRootNames.get(from);
		Node t = mRootNames.get(to);
		boolean[] visited = new boolean[mRoots.size()];
		return isReachable(f, t, visited);
	}
	
	private boolean isReachable(Node start, Node end, boolean[] visited) {
		if(start == end)
			return true;
		else {
			if(visited[start.mIdent])
				return false;
			visited[start.mIdent] = true;
			boolean reach = false;
			for(int i = 0; i < start.mSucc.size(); ++i) {
				if(!visited[start.mSucc.get(i).getNode().mIdent]) {
					reach = reach || isReachable(start.mSucc.get(i).getNode(), end, visited);
				}
			}
			visited[start.mIdent] = false;
			return reach;
		}
	}
	
	public int shortestPath(String from, String to) {
		Node start = mRootNames.get(from);
		Node end = mRootNames.get(to);
		boolean[] visited = new boolean[mRoots.size()];
		return shortestPath(start, end, visited, 0);
	}
	
	private int shortestPath(Node start, Node end, boolean[] visited, int len) {
		if(start == end)
			return len;
		else {
			if(visited[start.mIdent])
				return Integer.MAX_VALUE/2;
			visited[start.mIdent] = true;
			int way = Integer.MAX_VALUE/2;
			for(int i = 0; i < start.mSucc.size(); ++i) {
				if(!visited[start.mSucc.get(i).getNode().mIdent]) {
					int tmpLen = 1+len;
					way = Math.min(shortestPath(start.mSucc.get(i).getNode(), end, visited, tmpLen), way);
				}
			}
			visited[start.mIdent] = false;
			return way;
		}
	}
	
	public int shortestCheapestPath(String from, String to) {
		Node start = mRootNames.get(from);
		Node end = mRootNames.get(to);
		boolean[] visited = new boolean[mRoots.size()];
		return shortestCheapestPath(start, end, visited, 0);
	}
	
	private int shortestCheapestPath(Node start, Node end, boolean[] visited, int weight) {
		if(start == end)
			return weight;
		else {
			if(visited[start.mIdent])
				return Integer.MAX_VALUE/2;
			visited[start.mIdent] = true;
			int way = Integer.MAX_VALUE/2;
			for(int i = 0; i < start.mSucc.size(); ++i) {
				if(!visited[start.mSucc.get(i).getNode().mIdent]) {
					int tmpWeight = weight + start.mSucc.get(i).getWeight();
					way = Math.min(shortestCheapestPath(start.mSucc.get(i).getNode(), end, visited, tmpWeight), way);
				}
			}
			visited[start.mIdent] = false;
			return way;
		}
	}	
	
	public void print() {
		for(int i = 0; i < mRoots.size(); ++i) {
			LinkedList<Pair> tmp = mRoots.get(i).mSucc;
			String RootName = mRevRootNames.get(mRoots.get(i));
			System.out.print(RootName + ": ");
			for(int j = 0; j < tmp.size(); ++j) {
				String name = mRevRootNames.get(tmp.get(j).getNode());
				Integer weight = tmp.get(j).getWeight();
				if(j >= tmp.size() - 1)
					System.out.print(name + "/" + weight + " --> /");
				else
					System.out.print(name + "/" + weight + " --> ");
			}
			System.out.println();
		}
	}
}