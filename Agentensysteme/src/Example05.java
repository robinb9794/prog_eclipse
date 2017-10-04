import jade.core.Agent;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;

public class Example05 extends Agent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;

	protected void setup() {
		name = getLocalName();
		System.out.println(name + ": hello world");
		System.out.println(name + ": my startup parameters are: ");
		Object[] args = getArguments();
		if (args != null) {
			for (Object arg : args) {
				System.out.print("'" + arg + "' ");
			}
		}
		System.out.println();
	}

	public static void main(String args[]) {
		try {
			// create runtime instance
			Runtime runtime = Runtime.instance();

			// create main container
			Profile profile = new ProfileImpl();
			AgentContainer container = runtime.createMainContainer(profile);

			// create agent with name adam
			AgentController agent1 = container.createNewAgent("adam", Example05.class.getName(), args);
			agent1.start();

			// create rma agent to display
			AgentController rma = container.createNewAgent("rma", "jade.tools.rma.rma", args);
			rma.start();

		} catch (Exception e) {

		}
	}

}
