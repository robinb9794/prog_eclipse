package uebung11.aufgabe01;

import java.util.Hashtable;

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
		private final Func m_cThen, m_cElse;

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
}
