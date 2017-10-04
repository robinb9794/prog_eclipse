import jade.core.Agent;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.core.behaviours.Behaviour;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;

public class Example06 extends Agent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	class MyBehaviour extends Behaviour {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void action() {
			System.out.println(myAgent.getLocalName() + ": hello world");
		}

		@Override
		public boolean done() {
			// TODO Auto-generated method stub
			return true;
		}

	}

	protected void setup() {
		addBehaviour(new MyBehaviour());
	}

	public static void main(String args[]) {
		try {
			Runtime runtime = Runtime.instance();

			Profile profile = new ProfileImpl();
			AgentContainer container = runtime.createMainContainer(profile);

			AgentController agent1 = container.createNewAgent("robin", Example06.class.getName(), args);
			agent1.start();

			AgentController rma = container.createNewAgent("rma", "jade.tools.rma.rma", args);
			rma.start();
		} catch (Exception e) {

		}
	}

}
