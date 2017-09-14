package Listen;

public class StackOrQueueDoubleList<T> extends DoubleList<T> implements StackOrQueue<T> {

	boolean isStack;
	
	public StackOrQueueDoubleList(boolean isStack){
		this.isStack=isStack;
	}
	
	public static void main(String args[]){
		StackOrQueueDoubleList<Integer> soq = new StackOrQueueDoubleList<Integer>(true);
		for(int i = 0; i<10; i++){
			soq.add(i);
		}
		soq.print();
		System.out.println("Liste leer? "+(soq.isEmpty()?"Ja":"Nein"));
		System.out.println(">"+(soq.isStack?"Stack":"Queue")+"<, also ganz oben liegt: "+soq.top());
		soq.push(20);
		soq.print();
		soq.pop();
		soq.print();
	}
	
	@Override
	public boolean isEmpty() {
		return size()>0 ? false:true;
	}

	@Override
	public T top() {
		return isStack ? getM_Head().getElement() : getM_Tail().getElement();
	}

	@Override
	public void push(T elm) {
		push_front(elm);
	}

	@Override
	public void pop() {
		if(isStack){
			try {
				delete(0);
			} catch (OutOfBoundsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			try {
				delete(size()-1);
			} catch (OutOfBoundsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
