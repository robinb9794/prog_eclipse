package Binärbaum;

public class Node {
	public Node right, left;
	public int value;
	
	public Node(int value){
		this.value=value;
	}
	
	public Node getLeft(){
		return left!=null ? left:null;
	}
	
	public Node getRight(){
		return right!=null ? right:null;
	}

}
