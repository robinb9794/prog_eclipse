package uebung12.aufgabe02;

public class Pair {
	public Pair(Node n, Integer w) {
		mNode = n;
		mWeight = w;
	}
	
	public Integer getWeight() {
		return mWeight;
	}
	
	public Node getNode() {
		return mNode;
	}
	
	private Node mNode;
	private Integer mWeight;
}
