package zusatz;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Scanner;

public class TaskOperations {

	private static final String TASKLIST = "tasklist";

	public static boolean isProcessRunging(String serviceName) throws Exception {
		Process p = Runtime.getRuntime().exec(TASKLIST);
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				p.getInputStream()));
		String line;
		while ((line = reader.readLine()) != null) {

			System.out.println(line);
			if (line.contains(serviceName)) {
				return true;
			}
		}
		return false;
	}

	private static final String KILL = "taskkill /IM ";

	public static void killProcess(String serviceName) throws Exception {

		Runtime.getRuntime().exec(KILL + serviceName);

	}

	public static void main(String[] args) throws Exception {
		Process process = new ProcessBuilder().command("tasklist", "/V")
				.redirectErrorStream(true).start();
		Scanner scanner = new Scanner(process.getInputStream());
		StringWriter stringWriter = new StringWriter();
		PrintWriter printWriter = new PrintWriter(stringWriter);
		while (scanner.hasNextLine()) {
			printWriter.println(scanner.nextLine());
		}
		System.out.println(stringWriter);
	}
}
