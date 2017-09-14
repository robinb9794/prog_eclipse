import java.util.ArrayList;

import jade.core.Agent;
import jade.lang.acl.ACLMessage;

public class ReadMessageThread extends Thread {
	private Agent agent;
	private ArrayList<ACLMessage> messageQueue = new ArrayList<ACLMessage>();

	public ReadMessageThread(ArrayList<ACLMessage> messageQueue, Agent agent) {
		this.agent = agent;
		this.messageQueue = messageQueue;
	}

	public void run() {
		while (true) {
			ACLMessage msg = agent.receive();
			if (msg != null) {
				messageQueue.add(msg);
				System.out.println(msg);
			}
		}
	}
}
