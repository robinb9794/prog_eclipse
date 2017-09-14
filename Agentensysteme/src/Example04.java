import jade.core.Agent;
import jade.core.Location;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;

public class Example04 extends Agent {
	private static final long serialVersionUID = 1L;

	private String name;

	protected void setup() {
		name = getLocalName();
		Location loc = here();

		System.out.println(name + ":  Hello");
		System.out.println(name + " : my global name is " + getName());
		System.out.println(name + " : my state ist " + getAgentState());

		System.out.println(name + " : my location is " + loc.getName());
		System.out.println(name + " : the id of my location is " + loc.getID());
		System.out.println(name + " : the address of my location is " + loc.getAddress());
		System.out.println(name + " : the protocol of the location is " + loc.getProtocol());
		doDelete();
	}

	protected void takeDown() {
		System.out.println(name + " : bye bye");
	}

	public static void main(String args[]) {
		try {
			// create runtime instance
			Runtime runtime = Runtime.instance();

			// create main container
			Profile profile = new ProfileImpl();
			AgentContainer container = runtime.createMainContainer(profile);

			// create agent with name adam
			AgentController agent1 = container.createNewAgent("adam", Example04.class.getName(), args);
			agent1.start();

			// create rma agent to display
			AgentController rma = container.createNewAgent("rma", "jade.tools.rma.rma", args);
			rma.start();

		} catch (Exception e) {

		}
	}

}
