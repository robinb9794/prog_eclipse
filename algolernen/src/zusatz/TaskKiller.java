package zusatz;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TaskKiller {
	public static boolean isProcessRunging(String serviceName) throws Exception {
		String TASKLIST = "tasklist";
		Process p = Runtime.getRuntime().exec(TASKLIST);
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				p.getInputStream()));
		String line;
		while ((line = reader.readLine()) != null) {
			// if (line.contains(serviceName))
			// System.out.println(line);
			if (line.contains(serviceName)) {
				return true;
			}
		}
		return false;
	}

	public static void killProcess(String serviceName) throws Exception {
		String KILL = "taskkill /IM ";
		Runtime.getRuntime().exec(KILL + serviceName);
	}

	public static void main(String[] args) throws Exception {
		if (isProcessRunging("firefox.exe"))
			killProcess("firefox.exe");
	}
}
