package Listen;

public interface SimpleCollection<T> {
	int size();
	void push_back(T arg);
	void push_front(T arg);
	T get(int i) throws OutOfBoundsException;
	void set(int i, T arg) throws OutOfBoundsException;
	void delete(int i) throws OutOfBoundsException;
}

interface StackOrQueue<T>{
	boolean isEmpty();		//ist noch ein Element vorhanden?
	T top();				//liefert das aktuelle Element
	void push(T elm);		//legt ein neues Element ab
	void pop();				//entfernt das aktuelle Element
}
