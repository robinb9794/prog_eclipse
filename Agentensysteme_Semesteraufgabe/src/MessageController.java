import java.util.ArrayList;

import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;

public class MessageController {
	private static MessageController instance;
	private static AID gameAgentAID;
	private static Agent agent;
	private ReadMessageThread readMessageThread;
	private ArrayList<ACLMessage> messageQueue = new ArrayList<ACLMessage>();

	private MessageController() {
	}

	public MessageController getInstance() {
		return instance;
	}

	public static MessageController getInstance(AID gameAgentAID, Agent agent) {
		MessageController.gameAgentAID = gameAgentAID;
		MessageController.agent = agent;
		return new MessageController();
	}

	public void sendMessage(String content) {
		try {
			ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
			msg.addReceiver(gameAgentAID);
			msg.setSender(agent.getAID());
			msg.setInReplyTo(agent.getName());
			msg.setLanguage("JSON");
			msg.setContent(content);
			agent.send(msg);
			System.out.println(msg + "\n..................");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void readMessages() {
		// readMessageThread = new ReadMessageThread(messageQueue, agent);
		// readMessageThread.start();
		new Thread(new ReadMessageThread(messageQueue, agent)).start();
	}

	public synchronized void printLastIncomingMessage() {
		if (messageQueue.size() > 0) {
			if (messageQueue.size() - 1 < 0) {
				System.out.println(messageQueue.get(messageQueue.size()));
			} else {
				System.out.println(messageQueue.get(messageQueue.size() - 1));
			}
		}
	}
}
