package uebung09;

public class Node<K extends Comparable<K>, D> {
	
	public K m_Key;
	public D m_Data;
	public Node<K,D> m_Left;
	public Node<K,D> m_Right;
	public boolean m_bIsRed;
	
	public Node(K key, D data) {
		m_Key = key;
		m_Data = data;
		m_Left = null;
		m_Right = null;
		m_bIsRed = true;
	}
	
	public boolean isTwoNode() {
		return !m_bIsRed && (m_Left == null || !m_Left.m_bIsRed) && (m_Right == null || !m_Right.m_bIsRed);
	}
	
	public boolean isThreeNode() {
		return !m_bIsRed 
				&& ((m_Left != null && m_Left.m_bIsRed && (m_Right == null || !m_Right.m_bIsRed))
				|| (m_Right != null && m_Right.m_bIsRed && (m_Left == null || !m_Left.m_bIsRed)));
	}
	
	public boolean isFourNode() {
		return !m_bIsRed && m_Left != null && m_Left.m_bIsRed && m_Right != null && m_Right.m_bIsRed;
	}
	
	public boolean isLeaf() {
		return !m_bIsRed
				&& ((m_Left==null && m_Right==null)
				|| ((m_Left!=null&&m_Left.m_bIsRed&&m_Left.m_Left==null&&m_Left.m_Right==null&&m_Right==null) 
				|| (m_Right!=null&&m_Right.m_bIsRed&&m_Right.m_Left==null&&m_Right.m_Right==null&&m_Left==null)
				|| (m_Left!=null&&m_Left.m_bIsRed&&m_Right!=null&&m_Right.m_bIsRed&&m_Left.m_Left==null&&m_Left.m_Right==null&&m_Right.m_Left==null&&m_Right.m_Right==null)));
	}
	
	public boolean isSorted() {
		return (m_Left == null || m_Left.m_Key.compareTo(m_Key) < 0) &&  (m_Right == null || m_Right.m_Key.compareTo(m_Key) > 0);
	}
	
	public boolean isDoubleRed() {
		return m_bIsRed && (m_Left != null && m_Left.m_bIsRed) || (m_Right != null && m_Right.m_bIsRed);
	}
	
	public void convertFourNode() {
		if(isFourNode()) {
			m_bIsRed = true;
			m_Left.m_bIsRed = false;
			m_Right.m_bIsRed = false;
		}
	}
	
	@Override
	public String toString() {
		return m_Key + "/" + m_Data + "->" + (m_bIsRed ? "R" : "B");
	}
	
	public String printNode() {
		String edgeLeft  = "l(\"@"+m_Key+"-l\",e(\"edge\",[a(\"EDGEPATTERN\",\"single;solid;2;0\"),a(\"EDGECOLOR\",\"#ffffff\"),a(\"_DIR\",\"none\"),a(\"OBJECT\",\"\"),a(\"FONTSTYLE\",\"bold\"),a(\"INFO\",\"\")],r(\""+m_Key+"-l\")))";
		String edgeRight = "l(\"@"+m_Key+"-r\",e(\"edge\",[a(\"EDGEPATTERN\",\"single;solid;2;0\"),a(\"EDGECOLOR\",\"#ffffff\"),a(\"_DIR\",\"none\"),a(\"OBJECT\",\"\"),a(\"FONTSTYLE\",\"bold\"),a(\"INFO\",\"\")],r(\""+m_Key+"-r\")))";
		String nullNodeLeft  = "\r\tl(\""+m_Key+"-l\",n(\"node\",[a(\"OBJECT\",\"\"),a(\"_GO\",\"invisible\"),a(\"INFO\",\"\")],[])),";
		String nullNodeRight = "\r\tl(\""+m_Key+"-r\",n(\"node\",[a(\"OBJECT\",\"\"),a(\"_GO\",\"invisible\"),a(\"INFO\",\"\")],[])),";
		if(m_Left != null) {
			String colorLeft = m_Left.m_bIsRed ? "#ff0000" : "#000000";
			edgeLeft = "l(\"@"+m_Left.m_Key+"\",e(\"edge\",[a(\"EDGEPATTERN\",\"single;solid;2;0\"),a(\"EDGECOLOR\",\""+colorLeft+"\"),a(\"_DIR\",\"none\"),a(\"OBJECT\",\"\"),a(\"FONTSTYLE\",\"bold\"),a(\"INFO\",\"\")],r(\""+m_Left.m_Key+"\")))";
			nullNodeLeft = "";
		}			
		if(m_Right != null) { 
			String colorRight = m_Right.m_bIsRed ? "#ff0000" : "#000000";
			edgeRight = "l(\"@"+m_Right.m_Key+"\",e(\"edge\",[a(\"EDGEPATTERN\",\"single;solid;2;0\"),a(\"EDGECOLOR\",\""+colorRight+"\"),a(\"_DIR\",\"none\"),a(\"OBJECT\",\"\"),a(\"FONTSTYLE\",\"bold\"),a(\"INFO\",\"\")],r(\""+m_Right.m_Key+"\")))";
			nullNodeRight = "";
		}
		return "l(\""+m_Key+"\",n(\"node\",[a(\"COLOR\",\"#ffffff\"),a(\"OBJECT\",\""+m_Key+"\"),a(\"_GO\",\"circle\"),a(\"FONTFAMILY\",\"helvetica\"),a(\"FONTSTYLE\",\"normal\"),a(\"INFO\",\"\")],[\r\t"+edgeLeft+",\r\t"+edgeRight+"])),"
				+ nullNodeLeft + nullNodeRight;
	}
}