package zusatz.graphMatrix;

//Gegeben ist die folgende Implementierung einer Adjazenzmatrix f�r Graphen:
//
//public class GraphMatrix {
//    public GraphMatrix(int iNrOfNodes) {
//        m_Matrix = new boolean[iNrOfNodes][iNrOfNodes];
//    }
//    private boolean[][] m_Matrix;
//}
//
//Implementieren Sie eine Methode int shortestPath(int start,int end),
//die die L�nge des k�rzesten Wegs von start zu end berechnet (Tipp: rekursiv l�sen).

public class Main {
	public static void main(String[] args) {
//		GraphMatrix g = new GraphMatrix(nodes, false);
//		randEdges(g, nodes*2);
		
		GraphMatrix g = genStaticGraph();

		g.print();
		System.out.println("ShortestPath (1->6): " + g.shortestPath(1, 6));

		System.out.println("Tiefensuche: ");
		g.search(3, true);
		System.out.println("Breitensuche: ");
		g.search(3, false);
	}
	
	public static void randEdges(GraphMatrix g, int i) {
		for(int j=0; j<i; ++j) {
			int f = (int) (Math.random() * nodes);
			int t = (int) (Math.random() * nodes);
			if(f != t)
				g.addEdge(f, t);
		}
	}
	
	public static GraphMatrix genStaticGraph() {
		GraphMatrix g = new GraphMatrix(7, false);
		g.addEdge(0, 1);
		g.addEdge(1, 3);
		g.addEdge(1, 4);
		g.addEdge(1, 5);
		g.addEdge(2, 3);
		g.addEdge(2, 4);
		g.addEdge(4, 5);
		g.addEdge(4, 2);
		g.addEdge(6, 2);
		g.addEdge(6, 0);
		return g;
	}
	
	private static int nodes = 7;
}