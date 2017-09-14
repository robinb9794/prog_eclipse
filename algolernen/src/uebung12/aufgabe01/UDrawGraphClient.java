package uebung12.aufgabe01;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class UDrawGraphClient{

	private Socket s = null;
	private OutputStream out = null;
	private BufferedReader in = null;
	private boolean console;

	public UDrawGraphClient(boolean console,boolean bat) throws IOException {
		File batchFile = new File("../algolernen/toolz/uDrawGraph/server.bat");
		this.console=console;
		
		if (bat) {
			try {
				Desktop.getDesktop().open(batchFile);
			} catch (Exception e) {
				System.out.println("bat nicht gefunden");
			}
		} else {
			try {
				Runtime.getRuntime()
						.exec("cmd /c start ../algolernen/toolz/uDrawGraph/bin/uDrawGraph.exe -server");
			} catch (Exception e) {
				System.out.println("uDrawGraph nicht gefunden");
			}
		}

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
		}
		
		s = new Socket("localhost", 2542);
		out = s.getOutputStream();
		in = new BufferedReader(new InputStreamReader(s.getInputStream()));

		while (in.readLine().compareTo("ok") != 0)
			;
	}

	// Methoden
	// --------------------------------------------------------------------------------------------------
	public void newNode(String key) throws IOException {
		sendMsg("graph(mixed_update([new_node(\"" + key
				+ "\",\"C\",[a(\"OBJECT\",\"" + key + "\"),a(\"_GO\",\"circle\")])]))");
	}

	public void newEdgeDirected(String start, String end, Integer weight) throws IOException {
		sendMsg("graph(mixed_update([new_edge(\"" + start + ">"
				+ end + "\",\"B\",[a(\"OBJECT\",\""+weight+"\")],\"" + start + "\",\""
				+ end + "\")]))");
	}
	
	public void newEdgeUndirected(String start, String end, Integer weight) throws IOException {
		sendMsg("graph(mixed_update([new_edge(\"" + start + ">"
				+ end + "\",\"B\",[a(\"OBJECT\",\""+weight+"\"),a(\"_DIR\",\"none\")],\"" + start + "\",\""
				+ end + "\")]))");
	}

	public void newContext(String title) throws IOException {
		sendMsg("multi(new_context)");
		sendMsg("window(size(1024,768))");
		sendMsg("window(title(\"" + title + "\"))");
	}

	public boolean sendMsg(String b) throws IOException {
		out.write((b + "\n").getBytes());
		String temp = in.readLine();
		if(temp.compareTo("ok") != 0){
			if(console==true)
				System.out.println(temp=in.readLine());
			else
				temp=in.readLine();
			return false;
		}
		return true;
	}

	public void improve() throws IOException {
		sendMsg("menu(view(fit_scale_to_window))");
		sendMsg("menu(layout(improve_all))");
	}

	public void close() throws IOException {
		out.close();
		s.close();
	}
}