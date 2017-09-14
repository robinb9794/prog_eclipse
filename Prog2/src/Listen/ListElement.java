package Listen;

public class ListElement<T> {
	public ListElement next, prev;
	public T elem;
	
	public ListElement(T elem, ListElement next, ListElement prev){
		this.elem=elem;
		this.next=next;
		this.prev=prev;
	}
	
	public T getElem(){
		return this.elem;
	}
	
	public ListElement getNext(){
		return this.next;
	}
	
	public ListElement getPrev(){
		return this.prev;
	}
	
	public void setElem(T elem){
		this.elem=elem;
	}
	
	public void setNext(ListElement next){
		this.next=next;
	}
	
	public void setPrev(ListElement prev){
		this.prev=prev;
	}
}
