package uebung13;

// Aufgabe 1: 
// Implementieren Sie das Verfahren von Huffman zur Datenkomprimierung. 
// Testen Sie Ihr Programm, indem Sie beliebige Texte eingeben, diese 
// komprimieren lassen und die Komprimierung anschlieﬂend wieder 
// dekomprimieren. 

public class Aufgabe01 {
	
	Aufgabe01() {
		String strInput = "ADAACAAAB";
		Huffman huffman = new Huffman(strInput);
		
		System.out.println("String unkomprimiert: "+strInput+"\n");
		System.out.println("Binary unkomprimiert: "+huffman.getUncompressedBinaryString());
		
		// String komprimieren und ausgeben
		String strCompressed = huffman.encode();
		System.out.println("Binary komprimiert: "+strCompressed+"\n");

		// Komprimierungsfaktor ausgeben
		System.out.println("Komprimierte groesse im Verhaeltnis zur urspruenglichen Groesse: "+huffman.getCompressRatio()+" %\n");
		
		// Schauen ob die dekodierung klappt
		System.out.println("String dekomprimiert: "+huffman.decode(strCompressed));
		
	}

	public static void main(String[] args) {
		new Aufgabe01();
	}
}
