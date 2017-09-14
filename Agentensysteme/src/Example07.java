import jade.core.Agent;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.core.behaviours.Behaviour;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;

public class Example07 extends Agent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	class MyBehaviour extends Behaviour {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private final int id;
		private int step;

		public MyBehaviour(int id) {
			this.id = id;
			this.step = 0;
		}

		@Override
		public void action() {
			System.out.println(id + ": " + (step++));
		}

		@Override
		public boolean done() {
			// TODO Auto-generated method stub
			return false;
		}

	}

	protected void setup() {
		addBehaviour(new MyBehaviour(1));
		addBehaviour(new MyBehaviour(2));
	}

	public static void main(String args[]) {
		try {
			Runtime runtime = Runtime.instance();

			Profile profile = new ProfileImpl();

			AgentContainer container = runtime.createMainContainer(profile);

			AgentController agent1 = container.createNewAgent("robin", Example07.class.getName(), args);
			agent1.start();

			AgentController rma = container.createNewAgent("rma", "jade.tools.rma.rma", args);
			rma.start();
		} catch (Exception e) {

		}
	}

}
