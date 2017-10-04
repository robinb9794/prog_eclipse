package uebung11.aufgabe02;

import java.io.IOException;
import java.util.HashMap;

import uebung11.aufgabe02.RoBDD.Func;

public class RoBDD_Printer {

	UDrawGraphClient client = null;
	public boolean ready = false;
	int[] nodes = null;

	// Construktor
	public RoBDD_Printer(boolean console, boolean bat) throws IOException {
		client = new UDrawGraphClient(console, bat);
	}

	// rekursiver print mit uDrawGraph---------------------------------
	HashMap<String, Boolean> gefunden = new HashMap<String, Boolean>();

	public void print(String title, Func f) throws IOException {
		ready = false;
		client.newContext(title);
		print(f);
		client.improve();
		ready = true;
	}

	private void print(Func f) throws IOException {
		// Sharing. Wenn der Hashcode schon vorhanden , funktion wird nicht
		// angeschaut
		if (gefunden.containsKey(f.hashCode() + "") != true) {
			gefunden.put(f.hashCode() + "", true);
			// ---------------------------------------------------------------------------

			String node = f.hashCode() + "";
			String name = f.getName();
			client.newNode(node, name);

			Func thenFunc = f.getThen(f.getVar());
			if (thenFunc != null) {
				String thenString = thenFunc.hashCode() + "";
				print(thenFunc);
				client.newEdge1(node, thenString);
			}

			Func elseFunc = f.getElse(f.getVar());
			if (elseFunc != null) {
				String elseString = elseFunc.hashCode() + "";
				print(elseFunc);
				client.newEdge0(node, elseString);
			}
		}
	}
	// -------------------------------------------------------------------
}
