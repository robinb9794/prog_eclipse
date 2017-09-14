package uebung12.aufgabe01;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class GraphList {
	LinkedList<Node> mRoots;
	Hashtable<String, Node> mRootNames;
	Hashtable<Node, String> mRevRootNames;
	final boolean IS_BI_DIRECTED;

	public GraphList(boolean isBiDirected) {
		mRoots = new LinkedList<>();
		mRootNames = new Hashtable<String, Node>();
		mRevRootNames = new Hashtable<>();
		IS_BI_DIRECTED = isBiDirected;
	}

	public Node newNode(String nodeName) {
		Node newNode = mRootNames.get(nodeName);
		if (newNode == null) {
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
		if (f != null && t != null) {
			if (!f.mEdges.containsKey(t)) {
				f.mSucc.add(new Pair(t, weight));
				f.mEdges.put(t, true);
			}
			if (IS_BI_DIRECTED) {
				if (!t.mEdges.containsKey(f)) {
					t.mSucc.add(new Pair(f, weight));
					t.mEdges.put(f, true);
				}
			}
		}
	}

	class Tuple3Comparator implements Comparator<Tuple3<Node, Node, Integer>> {
		@Override
		public int compare(Tuple3<Node, Node, Integer> o1,
				Tuple3<Node, Node, Integer> o2) {
			return o1.mThird - o2.mThird;
		}
	}

	public GraphList getMinSpanTree(String node) {
		GraphList gl = new GraphList(IS_BI_DIRECTED);
		PriorityQueue<Tuple3<Node, Node, Integer>> ph = new PriorityQueue<>(
				mRoots.size(), new Tuple3Comparator());
		HashSet<Node> set = new HashSet<>();
		ph.add(new Tuple3<Node, Node, Integer>(mRootNames.get(node), mRootNames
				.get(node), 0));
		while (!ph.isEmpty()) {
			Tuple3<Node, Node, Integer> next = ph.poll();
			if (set.contains(next.mFirst))
				continue;
			set.add(next.mFirst);
			gl.addEdge(mRevRootNames.get(next.mSecond),
					mRevRootNames.get(next.mFirst),
					next.mSecond.getWeight(next.mFirst));
			for (Pair p : next.mFirst.mSucc) {
				if (!set.contains(p.getNode())) {
					ph.add(new Tuple3<Node, Node, Integer>(p.getNode(),
							next.mFirst, p.getWeight() + next.mThird));
				}
			}
		}
		return gl;
	}

	public HashSet<Node> getCycle() {
		Node node = mRoots.getFirst();
		HashSet<Node> res = new HashSet<Node>();
		PriorityQueue<Tuple3<Node, Node, Integer>> ph = new PriorityQueue<>(
				mRoots.size(), new Tuple3Comparator());
		HashSet<Node> set = new HashSet<>();
		ph.add(new Tuple3<Node, Node, Integer>(node, node, 0));
		while (!ph.isEmpty()) {
			Tuple3<Node, Node, Integer> next = ph.poll();
			if (set.contains(next.mFirst)) {
				res.add(next.mFirst);
				continue;
			}
			set.add(next.mFirst);
			for (Pair p : next.mFirst.mSucc) {
				if (!set.contains(p.getNode())) {
					ph.add(new Tuple3<Node, Node, Integer>(p.getNode(),
							next.mFirst, p.getWeight() + next.mThird));
				}
			}
		}
		return res;
	}

	public void getMySpanBaumBitch(String node) {
		PrioHeap ph = new PrioHeap(mRoots.size());
		boolean[] visited = new boolean[mRoots.size()];
		ph.insert(mRootNames.get(node).mIdent, 0.0f);

		while (!ph.isEmpty()) {
			float[] fDistance = new float[1];
			int iNextNode = ph.remove(fDistance);
			visited[iNextNode] = true;
			System.out.println(mRevRootNames.get(mRoots.get(iNextNode))
					+ " with distance " + fDistance[0]);
			for (int i = 0; i < mRoots.get(iNextNode).mSucc.size(); ++i) {
				float NEW_DISTANCE = mRoots.get(iNextNode).mSucc.get(i)
						.getWeight();
				if (!visited[mRoots.get(iNextNode).mSucc.get(i).getNode().mIdent]) {
					ph.insert(
							mRoots.get(iNextNode).mSucc.get(i).getNode().mIdent,
							NEW_DISTANCE + fDistance[0]);
				}
			}
		}
	}

	public void print() {
		for (int i = 0; i < mRoots.size(); ++i) {
			LinkedList<Pair> tmp = mRoots.get(i).mSucc;
			String RootName = mRevRootNames.get(mRoots.get(i));
			System.out.print(RootName + ": ");
			for (int j = 0; j < tmp.size(); ++j) {
				String name = mRevRootNames.get(tmp.get(j).getNode());
				Integer weight = tmp.get(j).getWeight();
				if (j >= tmp.size() - 1)
					System.out.print(name + "/" + weight + " --> /");
				else
					System.out.print(name + "/" + weight + " --> ");
			}
			System.out.println();
		}
	}
}