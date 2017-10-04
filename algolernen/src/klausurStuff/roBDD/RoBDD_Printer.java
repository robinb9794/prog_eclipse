package klausurStuff.roBDD;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import klausurStuff.roBDD.RoBDD.Func;

public class RoBDD_Printer {

	UDrawGraphClient client = null;
	int[] nodes = null;

	// Construktor
	public RoBDD_Printer(boolean console, boolean bat) throws IOException {
		client = new UDrawGraphClient(console, bat);
	}

	// rekursiver print mit uDrawGraph---------------------------------
	public int print(String title, Func f, String info) throws IOException {
		Set<Func> s = new HashSet<Func>();
		client.newContext(title);
		int tmp = print(f, s);
		client.improve();
		client.newInfo(info);
		client.setFontSize(14);
		return (tmp-2);
	}

	private int print(Func f, Set<Func> s) throws IOException {
		// Sharing. Wenn der Hashcode schon vorhanden , funktion wird nicht
		// angeschaut
		if (!s.contains(f)) {
			s.add(f);
			String node = f.hashCode() + "";
			String name = f.getName();

			// male node
			client.newNode(node, name);

			Func thenFunc = f.getThen(f.getVar());
			if (thenFunc != null) {
				String thenString = thenFunc.hashCode() + "";
				// rekursionsaufruf mit then
				print(thenFunc, s);
				// male 1-edge
				client.newEdge1(node, thenString);
			}

			Func elseFunc = f.getElse(f.getVar());
			if (elseFunc != null) {
				String elseString = elseFunc.hashCode() + "";
				// rekursionsaufruf mit else
				print(elseFunc, s);
				// male 0-edge
				client.newEdge0(node, elseString);
			}
		}
		return s.size();
	}
	// -------------------------------------------------------------------
}
