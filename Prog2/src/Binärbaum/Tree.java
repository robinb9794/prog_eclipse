package Binärbaum;

public class Tree implements SimpleCollection{
	Node root;
	int counterDeletedLeafs;
	
	public Tree(){
		root=null;
	}
	
	public void insert(int value){
		if(root==null){
			root=new Node(value);
		}else{
			insert(root,value);
		}
	}
	
	 public void insert(Node node, int value){
		 if(node.value>value){
			 if(node.left==null){
				 node.left=new Node(value);
			 }else{
				 insert(node.left,value);
			 }
		 }else{
			 if(node.right==null){
				 node.right=new Node(value);
			 }else{
				 insert(node.right,value);
			 }
		 }
	 }
	 
	 public int countNodes(Node n){
		 return n==null ? 0: 1+countNodes(n.left)+countNodes(n.right);
	 }
	 
	 public boolean find(int i, Node n, boolean found){
		 if(n.value==i){
			 return found=true;
		 }
		 if(n.left!=null){
			 found=find(i,n.left,found);
		 }
		 if(n.right!=null){
			 found=find(i,n.right,found);
		 }
		 return found;
	 }
	 
	 public int countContent(int i, Node n){
		 int counterLeft=0, counterRight=0,counterRoot=0,rememberRoot=i+1;
		 boolean rootChanged=false;
		 if(n==root){
			 if(n.value==i){
				 rememberRoot=n.value;
				 n.value=i+1;
				 rootChanged=true;
			 }
		 }
		 if(n.left!=null){
			 if(n.left.value==i){
				 counterLeft=1+countContent(i,n.left);
			 }else{
				 counterLeft=countContent(i,n.left);
			 }
		 }
		 if(n.right!=null){
			 if(n.right.value==i){
				 counterRight=1+countContent(i,n.right);
			 }else{
				 counterRight=countContent(i,n.right);
			 }
		 }
		 if(rootChanged){
			 root.value=rememberRoot;
		 }
		 return counterLeft+counterRight+counterRoot;
	 }
	 
	 public boolean isSorted(Node n, boolean sorted){
		 if(n.left!=null){
			 if(n.left.value<=n.value){
				 sorted=isSorted(n.left,sorted=true);
			 }else{
				 sorted=false;
			 }
		 }
		 if(n.right!=null){
			 if(n.right.value>=n.value){
				 sorted=isSorted(n.right,sorted=true);
			 }else{
				 sorted=false;
			 }
		 }
		 return sorted;
	 }
	 
	 public int sum(Node n){
		 int sum = n.value;
		 if(n.left!=null){
			 sum=sum+sum(n.left);
		 }
		 if(n.right!=null){
			 sum=sum+sum(n.right);
		 }
		 return sum;
	 }
	 
	 public float avg(){
		 float sum=sum(root);
		 float count=countNodes(root);
		 float avg=sum/count;
		 return avg;
	 }
	 
	 public void deleteLeafs(){
		 deleteLeafs_help(root);
	 }
	 
	 public void deleteLeafs_help(Node n){
		 if(n.left!=null){
			 if(n.left.left==null && n.left.right==null){
				 n.left=null;
				 ++counterDeletedLeafs;
			 }else{
				 deleteLeafs_help(n.left);
			 }
		 }
		 if(n.right!=null){
			 if(n.right.left==null && n.right.right==null){
				 n.right=null;
				 ++counterDeletedLeafs;
			 }else{
				 deleteLeafs_help(n.right);
			 }
		 }
	 }
	 
	 public void convert(SimpleCollection c){
		 convert(c,root);
	 }
	 
	 public void convert(SimpleCollection c, Node n){
		 if(n==null){
			 return;
		 }
		 convert(c,n.left);
		 c.push_back(n.value);
		 convert(c,n.right);
	 }
	 
	 int[] minMax(){
		 int[] mm = new int[2];
		 mm=minMax(root,9999999,-99999999,mm);
		 return mm;
	 }
	 
	 int[] minMax(Node n, int min, int max, int[] mm){
		 if(n.value<min){
			 min=n.value;
			 mm[0]=min;
		 }
		 if(n.value>max){
			 max=n.value;
			 mm[1]=max;
		 }
		 if(n.left!=null){
			 mm = minMax(n.left,min,max,mm);
		 }
		 if(n.right!=null){
			 mm=minMax(n.right,min,max,mm);
		 }
		 return mm;
	 }
	 
	 public static void insert(Tree tree){
			tree.insert(5);
			tree.insert(1);
			tree.insert(10);
			tree.insert(3);
			tree.insert(8);
			tree.insert(11);
			tree.insert(5);
			tree.insert(5);
			tree.insert(20);
			tree.insert(0);
		}
		
		public static void main(String args[]){
			Tree tree = new Tree();
			insert(tree);
			
			int counterContent = 0;
			int searchContent = 20;
			
			System.out.println("Es wurden "+tree.countNodes(tree.root)+" Knoten gefunden.");		
			System.out.println("Die Zahl "+counterContent+" gibt es "+tree.countContent(counterContent,tree.root)+" Mal.");
			System.out.println("Gibt es die Zahl "+searchContent+"? "+(tree.find(searchContent,tree.root,false) ==true ? "Ja" : "Nein"));
			System.out.println("Ist der Baum sortiert? "+(tree.isSorted(tree.root,false)==true?"Ja":"Nein"));
			System.out.println("Summe der Knoten: "+tree.sum(tree.root));
			System.out.println("Der Durchschnitt der Knoten beträgt "+tree.avg());
			tree.deleteLeafs();
			System.out.println(tree.counterDeletedLeafs+" Blätter wurden gelöscht.");
			System.out.println("Jetzt hat der Baum nur noch "+tree.countNodes(tree.root)+" Knoten.");
			
			int[] mm = new int[2];
			mm=tree.minMax();
			System.out.println("Minimum:" +mm[0]);
			System.out.println("Maximum: "+mm[1]);
		}

		@Override
		public int size() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public void push_back(Object arg) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void push_front(Object arg) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Object get(int i) throws OutOfBoundsException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void set(int i, Object arg) throws OutOfBoundsException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void delete(int i) {
			// TODO Auto-generated method stub
			
		}
	 
}

class OutOfBoundsException extends Exception{}

