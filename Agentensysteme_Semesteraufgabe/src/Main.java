import java.io.Serializable;

import jade.core.AID;
import jade.core.Agent;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;

public class Main extends Agent implements Serializable {
	private static final long serialVersionUID = 1L;
	private static AID gameAgentAID;
	private static MessageController msgController;
	Action action = new Action();

	public static void main(String args[]) {
		try {
			String host = "localhost"; // uni PC IP: 192.168.1.210
			int port = -1;
			String plattform = null;
			boolean main = false;

			Runtime runtime = Runtime.instance();

			Profile profile = new ProfileImpl(host, port, plattform, main);

			AgentContainer container = runtime.createAgentContainer(profile);

			AgentController myAnt = container.createNewAgent("Flick", Main.class.getName(), null);
			myAnt.start();

		} catch (Exception e) {
		}
	}

	public void setup() {
		action.birth();
		action.setColor("ANT_COLOR_BLUE");
		try {
			ServiceDescription filter = new ServiceDescription();
			filter.setType("antWorld2017");
			DFAgentDescription dfd = new DFAgentDescription();
			dfd.addServices(filter);

			DFAgentDescription[] results = DFService.search(this, dfd);
			gameAgentAID = results[0].getName();
			msgController = MessageController.getInstance(gameAgentAID, this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		msgController.sendMessage(
				"{\"type\": \"" + action.getCurrentAction() + "\",\n \"color\":\"" + action.getColor() + "\"}");
		while (true) { // muss optimiert werden
			msgController.printLastIncomingMessage();
		}
	}

}
