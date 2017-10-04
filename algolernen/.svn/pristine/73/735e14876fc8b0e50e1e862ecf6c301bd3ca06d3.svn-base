package uebung12.aufgabe02;

import java.io.IOException;
import java.util.Hashtable;

public class Graph_Printer {

	UDrawGraphClient client = null;
	public boolean ready = false;
	int[] nodes = null;

	// Construktor
	public Graph_Printer(boolean console, boolean bat) throws IOException {
		client = new UDrawGraphClient(console, bat);
	}

	// rekursiver print mit uDrawGraph---------------------------------
	public void print(String title, GraphList g) throws IOException {
		ready = false;
		client.newContext(title);
		print(g);
		client.improve();
		ready = true;
	}

	private void print(GraphList g) throws IOException {
		for (int i = 0; i < g.mRoots.size(); ++i) {
			client.newNode(g.mRevRootNames.get(g.mRoots.get(i)));
		}

		Hashtable<String, Boolean> hash = new Hashtable<>();
		for (int i = 0; i < g.mRoots.size(); ++i) {
			for (int a = 0; a < g.mRoots.get(i).mSucc.size(); ++a) {
				String start = g.mRevRootNames.get(g.mRoots.get(i));
				String end = g.mRevRootNames.get(g.mRoots.get(i).mSucc.get(a)
						.getNode());
				Integer weight = g.mRoots.get(i).mSucc.get(a).getWeight();

				if (!hash.contains(start + end)
						&& !hash.containsKey(end + start)) {
					hash.put(start + end, true);
					if (start.compareTo(end) != 0)
						client.newEdgeUndirected(start, end, weight);
				}
//				client.improve();
			}
		}
	}
	// -------------------------------------------------------------------
}
