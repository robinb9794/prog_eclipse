package Listen;

public class List<T> {
	ListElement head=null;
	ListElement tail=null;
	
	public void add(T elem){
		if(head==null){
			head = new ListElement<T>(elem,null,null);
			tail=head;
		}else{
			head= new ListElement<T>(elem,null,head);
			head.getPrev().setNext(head);
		}
	}
	
	public void delete(T elem2Delete){
		if(elem2Delete==head.getElem()){
			head=head.getPrev();
			head.getNext().setPrev(null);
			head.setNext(null);
		}else{
			for(ListElement elem=head;elem!=null;elem=elem.getPrev()){
				if(elem.getElem()==elem2Delete){
					elem.getPrev().setNext(elem.getNext());
					elem.getNext().setPrev(elem.getPrev());
					break;
				}
			}
		}
	}
	
	public void print(){
		for(ListElement elem=head; elem!=null; elem=elem.getPrev()){
			System.out.print(elem.getElem()+"\t");
		}
		System.out.println("Kopf: "+head.getElem());
	}
	
	public static void main(String args[]){
		List<Integer> list = new List<Integer>();
		for(int i = 0; i<10; i++){
			list.add(i);
		}
		list.print();
	}
}
