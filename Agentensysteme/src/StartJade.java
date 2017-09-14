import jade.core.Agent;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;

public class StartJade extends Agent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void setup() {
		System.out.println("Hallo, mein Name ist " + getLocalName());
	}

	public static void main(String args[]) {
		try {
			// create runtime instance
			Runtime runtime = Runtime.instance();

			// create main container
			Profile profile = new ProfileImpl();
			AgentContainer container = runtime.createMainContainer(profile);

			// create agent with name adam
			AgentController agent1 = container.createNewAgent("adam", StartJade.class.getName(), args);
			agent1.start();

			// create name with name eva
			AgentController agent2 = container.createNewAgent("eva", StartJade.class.getName(), args);
			agent2.start();

			// create rma agent to display
			AgentController rma = container.createNewAgent("rma", "jade.tools.rma.rma", args);
			rma.start();

		} catch (Exception e) {

		}
	}
}
