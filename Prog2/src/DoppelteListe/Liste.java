package DoppelteListe;

public class Liste {
	private element head;
	private element tail;
	
	class element{
		private element next;
		private element prev;
		public int value;
		
		public element(int obj, element newNext, element newPrev){
			next=newNext;
			prev=newPrev;
			value=obj;
			
			if(next!=null){
				next.setPrev(this);
			}
			if(prev!=null){
				prev.setNext(this);
			}
		}
		
		public void setElement(int arg){
			value=arg;
		}
		
		public int getElement(){
			return value;
		}
		
		public element getNext(){
			return next;
		}
		
		public element getPrev(){
			return prev;
		}
		
		private void setNext(element newNext){
			next=newNext;
		}
		
		private void setPrev(element newPrev){
			prev=newPrev;
		}
	}
	
	public Liste(){
		head=null;
		tail=null;
		
		element tmp = new element(0,null,null);
		tmp.next=null;
	}
	
	public void addElementHead(int obj){
		if(head!=null){
			head=new element(obj,head,null);
		}else{
			tail=head=new element(obj,head,null);
		}
	}
	
	public void addElementTail(int obj){
		if(head!=null){
			tail=new element(obj,null,tail);
		}else{
			addElementHead(obj);
		}
	}
	
	public void print(){
		for(element elem = head; elem!=null; elem=elem.getNext()){
			System.out.print(elem.getElement()+" | ");
		}
		System.out.println();
	}
	
	public void printBack(){
		for(element elem = tail; elem!=null;elem=elem.getPrev()){
			System.out.print(elem.getElement()+" | ");
		}
		System.out.println();
	}
	
	public boolean isEmpty(){
		return !(head==null);
	}
	
	public int getTop(){
		return head.getElement();
	}
	
	public int getTail(){
		return tail.getElement();
	}
	
	public void deleteElem(element elem2Delete){
		if(isEmpty()){
			if(head==tail){
				head=tail=null;
			}else{
				if(head==elem2Delete){
					head=elem2Delete.getNext();
					head.setPrev(null);
				}else{
					if(tail==elem2Delete){
						tail=elem2Delete.getPrev();
						tail.setNext(null);
					}else{
						elem2Delete.getPrev().setNext(elem2Delete.getNext());
						elem2Delete.getNext().setPrev(elem2Delete.getPrev());
					}
				}
			}
		}
	}
	
	public void deleteHead(){
		deleteElem(head);
	}
	
	public void deleteTail(){
		deleteElem(tail);
	}
	
	public void swap(element a, element b){
		if(a!=head && b != tail){
			b.getNext().setPrev(a);
			a.getPrev().setNext(b);
		}else{
			if(a==head){
				head=b;
				b.getNext().setPrev(a);
			}
			if(b==tail){
				tail=a;
				a.getPrev().setNext(b);
			}
		}
		a.setNext(b.getNext());
		a.setPrev(b.getPrev());
		
		b.setNext(a.getNext());
		b.setPrev(a.getPrev());
	}
	
	public void delete(int start, int end){
		if(start>end){
			return;
		}else{
			element helperStart = head;
			element helperEnd=head;
			
			for(int i=1; i<start;i++){
				helperStart=helperStart.getNext();
			}
			
			for(int i = 1;i<end;i++){
				helperEnd=helperEnd.getNext();
			}
			
			helperStart.getPrev().setNext(helperEnd.getNext());
			helperEnd.getNext().setPrev(helperStart.getPrev());
		}
	}
	
	public static void main(String args[]){
		Liste list = new Liste();
		for(int i = 12; i>0; i--){
			list.addElementHead(i);
		}
		list.print();
		list.delete(1,2);
		list.print();
	}
}
