package klausurStuff.redBlackTreeJan;

public class Node<K extends Comparable<K>, D> {
	public Node(K key, D data) {
		mKey = key;
		mData = data;
		mLeft = null;
		mRight = null;
		mIsRed = true;
	}
	
	public boolean isTwoNode() {
		return !mIsRed && (mLeft == null || !mLeft.mIsRed) && (mRight == null || !mRight.mIsRed);
	}
	
	public boolean isThreeNode() {
		return !mIsRed 
				&& ((mLeft != null && mLeft.mIsRed && (mRight == null || !mRight.mIsRed))
				|| (mRight != null && mRight.mIsRed && (mLeft == null || !mLeft.mIsRed)));
	}
	
	public boolean isFourNode() {
		return !mIsRed && mLeft != null && mLeft.mIsRed && mRight != null && mRight.mIsRed;
	}
	
	public boolean isLeaf() {
		return !mIsRed
				&& ((mLeft==null && mRight==null)
				|| ((mLeft!=null&&mLeft.mIsRed&&mLeft.mLeft==null&&mLeft.mRight==null&&mRight==null) 
				|| (mRight!=null&&mRight.mIsRed&&mRight.mLeft==null&&mRight.mRight==null&&mLeft==null)
				|| (mLeft!=null&&mLeft.mIsRed&&mRight!=null&&mRight.mIsRed&&mLeft.mLeft==null&&mLeft.mRight==null&&mRight.mLeft==null&&mRight.mRight==null)));
	}
	
	public boolean isSorted() {
		return (mLeft == null || mLeft.mKey.compareTo(mKey) < 0) &&  (mRight == null || mRight.mKey.compareTo(mKey) > 0);
	}
	
	public boolean isDoubleRed() {
		return mIsRed && (mLeft != null && mLeft.mIsRed) || (mRight != null && mRight.mIsRed);
	}
	
	public void convertFourNode() {
		if(isFourNode()) {
			mIsRed = true;
			mLeft.mIsRed = false;
			mRight.mIsRed = false;
		}
	}
	
	@Override
	public String toString() {
		return mKey + "/" + mData + "->" + (mIsRed ? "R" : "B");
	}
	
	public String printNode() {
		String edgeLeft  = "l(\"@"+mKey+"-l\",e(\"edge\",[a(\"EDGEPATTERN\",\"single;solid;2;0\"),a(\"EDGECOLOR\",\"#ffffff\"),a(\"_DIR\",\"none\"),a(\"OBJECT\",\"\"),a(\"FONTSTYLE\",\"bold\"),a(\"INFO\",\"\")],r(\""+mKey+"-l\")))";
		String edgeRight = "l(\"@"+mKey+"-r\",e(\"edge\",[a(\"EDGEPATTERN\",\"single;solid;2;0\"),a(\"EDGECOLOR\",\"#ffffff\"),a(\"_DIR\",\"none\"),a(\"OBJECT\",\"\"),a(\"FONTSTYLE\",\"bold\"),a(\"INFO\",\"\")],r(\""+mKey+"-r\")))";
		String nullNodeLeft  = "\r\tl(\""+mKey+"-l\",n(\"node\",[a(\"OBJECT\",\"\"),a(\"_GO\",\"invisible\"),a(\"INFO\",\"\")],[])),";
		String nullNodeRight = "\r\tl(\""+mKey+"-r\",n(\"node\",[a(\"OBJECT\",\"\"),a(\"_GO\",\"invisible\"),a(\"INFO\",\"\")],[])),";
		if(mLeft != null) {
			String colorLeft = mLeft.mIsRed ? "#ff0000" : "#000000";
			edgeLeft = "l(\"@"+mLeft.mKey+"\",e(\"edge\",[a(\"EDGEPATTERN\",\"single;solid;2;0\"),a(\"EDGECOLOR\",\""+colorLeft+"\"),a(\"_DIR\",\"none\"),a(\"OBJECT\",\"\"),a(\"FONTSTYLE\",\"bold\"),a(\"INFO\",\"\")],r(\""+mLeft.mKey+"\")))";
			nullNodeLeft = "";
		}			
		if(mRight != null) { 
			String colorRight = mRight.mIsRed ? "#ff0000" : "#000000";
			edgeRight = "l(\"@"+mRight.mKey+"\",e(\"edge\",[a(\"EDGEPATTERN\",\"single;solid;2;0\"),a(\"EDGECOLOR\",\""+colorRight+"\"),a(\"_DIR\",\"none\"),a(\"OBJECT\",\"\"),a(\"FONTSTYLE\",\"bold\"),a(\"INFO\",\"\")],r(\""+mRight.mKey+"\")))";
			nullNodeRight = "";
		}
		return "l(\""+mKey+"\",n(\"node\",[a(\"COLOR\",\"#ffffff\"),a(\"OBJECT\",\""+mKey+"\"),a(\"_GO\",\"circle\"),a(\"FONTFAMILY\",\"helvetica\"),a(\"FONTSTYLE\",\"normal\"),a(\"INFO\",\"\")],[\r\t"+edgeLeft+",\r\t"+edgeRight+"])),"
				+ nullNodeLeft + nullNodeRight;
	}
	
	public K mKey;
	public D mData;
	public Node<K,D> mLeft;
	public Node<K,D> mRight;
	public boolean mIsRed;
}