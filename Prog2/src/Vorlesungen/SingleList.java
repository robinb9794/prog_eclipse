package Vorlesungen;

public class SingleList<T>{
	class ListElem{
		public ListElem(T obj, ListElem next){
			m_Next=next;
			m_Elem=obj;
		}
		private ListElem m_Next;
		private T m_Elem;
	}
	private ListElem m_Head;
	
	public SingleList(){
		m_Head=null;
	}
	
	public void add(T obj){
		m_Head=new ListElem(obj,m_Head);
	}
	
	public void deleteEverySecond(){
		ListElem helper = m_Head;
		for(ListElem elem = m_Head;elem!=null;elem=helper){			
			try{
				add(elem.m_Elem);
				helper=helper.m_Next.m_Next;
			}catch(Exception e){
				helper=null;
			}
		}
	}
	
	public static void main(String args[]){
		SingleList<Integer> sl = new SingleList<Integer>();
		for(int i = 0; i<15;++i){
			sl.add(i);
		}
		sl.print();
		System.out.println();
		sl.m_Head=null;
		sl.deleteEverySecond();
		sl.print();
	}
	
	public void print(){
		for(ListElem elem = m_Head;elem!=null;elem=elem.m_Next){
			System.out.print(elem.m_Elem + "\t");
		}
	}
}
