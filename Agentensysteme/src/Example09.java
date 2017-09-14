import jade.core.Agent;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.core.behaviours.WakerBehaviour;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;

public class Example09 extends Agent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private OneShotBehaviour oneB;
	private CyclicBehaviour cycB;
	private TickerBehaviour ticB;
	private WakerBehaviour wakB;

	protected void setup() {
		/**
		 * Behaviour-Klasse, die nur ein einziges Mal abarbeitet. done() = true
		 */
		oneB = new OneShotBehaviour(this) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void action() {
				System.out.println("OneShot: exec");
			}

			public void onStart() {
				System.out.println("OneShot: start");
			}

			public int onEnd() {
				System.out.println("OneShot: end!?");
				return 0;
			}

		};

		/**
		 * Behaviour-Klasse, die Anweisungen wiederholt abarbeitet. done() =
		 * false
		 */
		cycB = new CyclicBehaviour(this) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void action() {
				System.out.println("Cyclic: exec");
			}

			public void onStart() {
				System.out.println("Cyclic: start");
			}

			public int onEnd() {
				System.out.println("Cyclic: end!?");
				return 0;
			}
		};

		/**
		 * Behaviour-Klasse, die Anweisungen in vorgegebenen Zeitabständen
		 * periodisch abarbeitet: onTick()
		 */
		ticB = new TickerBehaviour(this, 10) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void onTick() {
				System.out.println("Ticker: exec");
			}

			public void onStart() {
				System.out.println("Ticker: start");
			}

			public int onEnd() {
				System.out.println("Ticker: end!?");
				return 0;
			}
		};

		/**
		 * Behaviour-Klasse, die Anweisungen ein Mal nach einer vorgegebenen
		 * Zeit abarbeitet: onWake()
		 */
		wakB = new WakerBehaviour(this, 50) {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void onWake() {
				System.out.println("Waker: exec");
			}

			public void onStart() {
				System.out.println("Waker: start");
			}

			public int onEnd() {
				System.out.println("Waker: end!?");
				myAgent.removeBehaviour(cycB);
				myAgent.removeBehaviour(ticB);
				return 0;
			}

		};

		addBehaviour(cycB);
		addBehaviour(ticB);
		addBehaviour(oneB);
		addBehaviour(wakB);
	}

	public static void main(String args[]) {
		try {
			Runtime runtime = Runtime.instance();

			Profile profile = new ProfileImpl();

			AgentContainer container = runtime.createMainContainer(profile);

			AgentController agent1 = container.createNewAgent("robin", Example09.class.getName(), args);
			agent1.start();

			AgentController rma = container.createNewAgent("rma", "jade.tools.rma.rma", args);
			rma.start();
		} catch (Exception e) {

		}
	}

}
