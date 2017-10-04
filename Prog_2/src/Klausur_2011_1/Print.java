package Klausur_2011_1;

public class Print extends Magic implements Runnable {
	String m_Str;

	Print(String str) {
		m_Str = str;
	}

	void print() {
		System.out.println(m_Str);
	}

	public static void main(String args[]) {
		new Thread(new Print("str 1")).start();
		new Thread(new Print("str 2")).start();
	}

	@Override
	public void run() {
		while (true) {
			print();
		}
	}
}

class Magic {
	void ichMacheNix() {
	}
}
