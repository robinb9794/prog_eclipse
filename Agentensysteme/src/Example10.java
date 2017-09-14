import jade.core.Agent;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.SequentialBehaviour;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;

public class Example10 extends Agent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	class MyOneShotBehaviour extends OneShotBehaviour {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private final int n, m;

		public MyOneShotBehaviour(int n, int m) {
			this.n = n;
			this.m = m;
		}

		@Override
		public void action() {
			System.out.println(n + ": " + m);
		}

	}

	/**
	 * Zusammengesetzte Behaviour-Klasse, die ihre Sub Behaviours nacheinander
	 * ausführt. Der Behaviour ist genau dann beendet, wenn alle Sub Behaviours
	 * beendet sind
	 * 
	 * @author Robin
	 *
	 */
	class MySequentialBehaviour extends SequentialBehaviour {

		private static final long serialVersionUID = 1L;

		private final int n;

		public MySequentialBehaviour(int n) {
			this.n = n;
			for (int i = 0; i < 10; ++i) {
				addSubBehaviour(new MyOneShotBehaviour(n, i));
			}
		}

		public int onEnd() {
			System.out.println(n + ": finished");
			return 0;
		}

	}

	protected void setup() {
		/**
		 * Zusammengesetzte Behaviour-Klasse, die ihre Sub Behaviours
		 * nebenläufig ausführt. Die Abbruchbedingung wird im Konstruktor
		 * spezifiziert (WHEN ALL, WHEN ANY)
		 */
		ParallelBehaviour p = new ParallelBehaviour(this, ParallelBehaviour.WHEN_ALL);
		p.addSubBehaviour(new MySequentialBehaviour(1));
		p.addSubBehaviour(new MySequentialBehaviour(2));
		p.addSubBehaviour(new MySequentialBehaviour(3));
		addBehaviour(p);
	}

	public static void main(String args[]) {
		try {
			Runtime runtime = Runtime.instance();

			Profile profile = new ProfileImpl();

			AgentContainer container = runtime.createMainContainer(profile);

			AgentController agent1 = container.createNewAgent("robin", Example10.class.getName(), args);
			agent1.start();

			AgentController rma = container.createNewAgent("rma", "jade.tools.rma.rma", args);
			rma.start();
		} catch (Exception e) {

		}
	}

}
