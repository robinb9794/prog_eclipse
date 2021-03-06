package klausurStuff.roBDD;

/**
 * 400. Revision Bitch! ;o)
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

public class RoBDD {

	Hashtable<Triple, Func> m_Unique;
	static Hashtable<Integer, String> m_FuncNames;
	private Func m_cTrue = new Func(true);
	private Func m_cFalse = new Func(false);
	int varCount;

	RoBDD() {
		m_cTrue = new Func(true);
		m_cFalse = new Func(false);
		m_Unique = new Hashtable<Triple, Func>();
		m_FuncNames = new Hashtable<Integer, String>();
		varCount = 0;

		// sollte nochmal iwie anders gemacht werden...wenn wer wei� wie!!--
		m_FuncNames.put(2147483646, "0");
		m_FuncNames.put(2147483647, "1");
		// ------------------------------------------------------------------
		// warum: wenn diese zahl von varCount erreicht wird gibt es eine
		// doppelte vergabe und es kann nicht gezeichnet werden! eine m�gliche
		// l�sung: big integer f�r varCount, die zahlen mit einer ifabfrage
		// ueberspringen und dann normal weiterz�hlen...
	}

	Func genVar(String string) {
		Triple entry = new Triple(varCount, genTrue(), genFalse());
		Func res = m_Unique.get(entry);
		if (res == null) {
			res = new Func(varCount, genTrue(), genFalse());
			m_Unique.put(entry, res);
			m_FuncNames.put(varCount, string);
			varCount++;
		}
		return res;
	}

	Func genTrue() {
		return m_cTrue;
	}

	Func genFalse() {
		return m_cFalse;
	}

	Func ite(Func i, Func t, Func e) {
		if (i.isTrue())
			return t;
		else if (i.isFalse())
			return e;
		else if (t.isTrue() && e.isFalse())
			return i;
		else {
			final int ciVar = Math.min(Math.min(i.getVar(), t.getVar()),
					e.getVar());
			final Func T = ite(i.getThen(ciVar), t.getThen(ciVar),
					e.getThen(ciVar));
			final Func E = ite(i.getElse(ciVar), t.getElse(ciVar),
					e.getElse(ciVar));
			if (T.equals(E))
				return T;
			final Triple entry = new Triple(ciVar, T, E);
			Func res = m_Unique.get(entry);
			if (res == null) {
				res = new Func(ciVar, T, E);
				m_Unique.put(entry, res);
			}
			return res;
		}
	}

	// logische operationen -------------------------------------
	Func and(Func a, Func b) {
		return ite(a, b, genFalse());
	}

	Func or(Func a, Func b) {
		return ite(a, genTrue(), ite(b, genTrue(), genFalse()));
	}

	Func not(Func a) {
		return ite(a, genFalse(), genTrue());
	}

	Func implikation(Func a, Func b) {
		return ite(a, ite(b, genTrue(), genFalse()), genTrue());
	}

	Func aequivalenz(Func a, Func b) {
		return ite(a, ite(b, genTrue(), genFalse()),
				ite(b, genFalse(), genTrue()));
	}

	// ---------------------------------------------------------

	// Consolenausgabe der HashMaps
	public void printHashMaps() {
		for (Triple tripel : m_Unique.keySet()) {
			System.out.println("ich: " + tripel.m_ciVar + " ("
					+ m_FuncNames.get(tripel.m_ciVar) + ") | then: "
					+ tripel.m_cThen.m_ciVar + " ("
					+ m_FuncNames.get(tripel.m_cThen.m_ciVar) + ") | else: "
					+ tripel.m_cElse.m_ciVar + " ("
					+ m_FuncNames.get(tripel.m_cElse.m_ciVar) + ")");
		}
	}

	// ---------------------------------------------------------

	static final class Triple {
		final int m_ciVar;
		final Func m_cThen;
		final Func m_cElse;

		Triple(int iVar, Func fThen, Func fElse) {
			m_ciVar = iVar;
			m_cThen = fThen;
			m_cElse = fElse;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj instanceof Triple) {
				Triple arg = (Triple) obj;
				return arg.m_ciVar == m_ciVar && arg.m_cThen == m_cThen
						&& arg.m_cElse == m_cElse;
			}
			return false;
		}

		@Override
		public int hashCode() {
			return m_ciVar ^ m_cThen.hashCode() ^ m_cElse.hashCode();
		}
	}

	// ---------------------------------------------------------
	public static final class Func {
		private static final int TRUE = 0x7fffffff;
		private static final int FALSE = TRUE - 1;
		final int m_ciVar;
		final Func m_cThen;
		final Func m_cElse;

		Func(boolean b) {
			m_ciVar = b ? TRUE : FALSE;
			m_cThen = m_cElse = null;
		}

		Func(int iVar, Func t, Func e) {
			m_ciVar = iVar;
			m_cThen = t;
			m_cElse = e;
		}

		String getName() {
			return m_FuncNames.get(m_ciVar);
		}

		Func getThen(int iVar) {
			return iVar == m_ciVar ? m_cThen : this;
		}

		Func getElse(int iVar) {
			return iVar == m_ciVar ? m_cElse : this;
		}

		int getVar() {
			return m_ciVar;
		}

		boolean isTrue() {
			return m_ciVar == TRUE;
		}

		boolean isFalse() {
			return m_ciVar == FALSE;
		}

		boolean isConstant() {
			return isTrue() || isFalse();
		}
	}

	// Minimale Tiefe des Baumes
	public int minDepth(Func f) {
		Map<Func, Integer> hash = new HashMap<>();
		return minDepth(f, hash);
	}

	private int minDepth(Func f, Map<Func, Integer> h) {
		if (f.isConstant())
			return 0;
		if (!h.containsKey(f))
			h.put(f, 1 + Math.min(minDepth(f.m_cThen, h),
					minDepth(f.m_cElse, h)));
		return h.get(f);
	}

	// Maximale Tiefe des Baumes
	public int maxDepth(Func f) {
		Map<Func, Integer> hash = new HashMap<>();
		return maxDepth(f, hash);
	}

	private int maxDepth(Func f, Map<Func, Integer> h) {
		if (f.isConstant())
			return 0;
		if (!h.containsKey(f))
			h.put(f, 1 + Math.max(maxDepth(f.m_cThen, h),
					maxDepth(f.m_cElse, h)));
		return h.get(f);
	}

	// Knoten z�hlen
	public int countNodes(Func f) {
		Set<Integer> m = new HashSet<Integer>();
		return countNodes(f, m);
	}

	private int countNodes(Func f, Set<Integer> m) {
		if (!f.isConstant()) {
			if (!m.contains(f))
				m.add(f.hashCode());
			countNodes(f.m_cElse, m);
			countNodes(f.m_cThen, m);
			return m.size();
		}
		else
//			m.add(f.hashCode()); // 0 und 1 mitz�hlen
			return 0;
	}
	
	// kanten von func a zu func b z�hlen
	public int distanceAB(Func a,Func b) {
		Map<Func,Integer> m = new HashMap<Func,Integer>();
		return distanceAB(a,b, m);
	}

	private int distanceAB(Func a,Func b, Map<Func,Integer> m) {
		if (a.isConstant()||a==b)
			return 0;
		if (!m.containsKey(a))
			m.put(a, 1 + Math.max(distanceAB(a.m_cThen,b, m),
					distanceAB(a.m_cElse,b,m)));
		return m.get(a);
	}

	// Ist die Funktion f erf�llbar?
	public boolean isSat(Func f) {
		Map<Func, Boolean> hash = new HashMap<>();
		return isSat(f, hash);
	}

	private boolean isSat(Func f, Map<Func, Boolean> h) {
		if (f.isConstant())
			return f.isTrue();
		if (!h.containsKey(f))
			h.put(f, isSat(f.m_cThen, h) || isSat(f.m_cElse, h));
		return h.get(f);
	}
}