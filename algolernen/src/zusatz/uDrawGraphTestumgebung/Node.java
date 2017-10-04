package zusatz.uDrawGraphTestumgebung;

public class Node<K extends Comparable <K>,D> {
    public Node(K key,D data) {
        m_Key = key; m_Data = data; m_bIsRed = true;
    }
    K m_Key;
    D m_Data;
    Node<K,D> m_Left = null;
    Node<K,D> m_Right = null;
    boolean m_bIsRed = true;

    public boolean is4Node() {
        return m_Left != null && m_Left.m_bIsRed &&
           m_Right != null && m_Right.m_bIsRed;
    }
    
    public boolean is2Node(){
    	return !m_bIsRed 
    			&& (m_Left == null || !m_Left.m_bIsRed)
    			&& (m_Right == null || !m_Right.m_bIsRed);
    }
    
    void convert4Node() {
        m_Left.m_bIsRed = false;
        m_Right.m_bIsRed= false;
        m_bIsRed = true;
    }
}
