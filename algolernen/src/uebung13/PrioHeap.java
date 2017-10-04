package uebung13;

class PrioHeap {

	int m_iNextFree;
	float[] m_Keys;
	int[] m_Entries;
	int[] m_PlaceInHeap;

	public PrioHeap(int iNrOfNodes) {
		m_iNextFree = 0;
		m_Keys = new float[iNrOfNodes];
		m_Entries = new int[iNrOfNodes];
		m_PlaceInHeap = new int[iNrOfNodes];
		for (int i = 0; i < iNrOfNodes; ++i) {
			m_PlaceInHeap[i] = iNrOfNodes;
		}
	}

	public void insert(int iNode, float key) {
		final int PLACE_IN_HEAP = m_PlaceInHeap[iNode];
		if (PLACE_IN_HEAP != m_Keys.length) {
			// update
			if (m_Keys[PLACE_IN_HEAP] > key) {
				// new, lower priority
				m_Keys[PLACE_IN_HEAP] = key;
				upHeap(PLACE_IN_HEAP);
			}
		} else {
			// new entry
			m_Keys[m_iNextFree] = key;
			m_Entries[m_iNextFree] = iNode;
			m_PlaceInHeap[iNode] = m_iNextFree;
			upHeap(m_iNextFree);
			++m_iNextFree;
		}
	}

	public int remove(float[] key) {
		key[0] = m_Keys[0];
		final int NODE = m_Entries[0];
		m_PlaceInHeap[NODE] = m_Keys.length;
		m_Keys[0] = m_Keys[--m_iNextFree];
		m_Entries[0] = m_Entries[m_iNextFree];
		m_PlaceInHeap[m_Entries[0]] = 0;
		downHeap(0);
		return NODE;
	}

	public boolean isEmpty() {
		return m_iNextFree == 0;
	}
	
	public int size() {
		return m_iNextFree;
	}

	private void upHeap(int iIndex) {
		final float VAL = m_Keys[iIndex];
		final int NODE = m_Entries[iIndex];
		int iFather = (iIndex - 1) / 2;
		while (iIndex != 0 && m_Keys[iFather] > VAL) {
			m_Keys[iIndex] = m_Keys[iFather];
			m_Entries[iIndex] = m_Entries[iFather];
			m_PlaceInHeap[m_Entries[iIndex]] = iIndex;
			iIndex = iFather;
			iFather = (iIndex - 1) / 2;
		}
		m_Keys[iIndex] = VAL;
		m_Entries[iIndex] = NODE;
		m_PlaceInHeap[NODE] = iIndex;
	}

	void downHeap(int iIndex) {
		final float KEY = m_Keys[iIndex];
		final int NODE = m_Entries[iIndex];
		while (iIndex < m_iNextFree / 2) {
			int iSon = 2 * iIndex + 1;
			if (iSon < m_iNextFree - 1 && m_Keys[iSon] > m_Keys[iSon + 1])
				++iSon;
			if (KEY <= m_Keys[iSon])
				break;
			m_Keys[iIndex] = m_Keys[iSon];
			m_Entries[iIndex] = m_Entries[iSon];
			m_PlaceInHeap[m_Entries[iIndex]] = iIndex;
			iIndex = iSon;
		}
		m_Keys[iIndex] = KEY;
		m_Entries[iIndex] = NODE;
		m_PlaceInHeap[NODE] = iIndex;
	}
	
	public void printAllNodes(){
		for(int i=0;i<m_iNextFree;++i)
			System.out.println(m_Entries[i]);
	}
}
