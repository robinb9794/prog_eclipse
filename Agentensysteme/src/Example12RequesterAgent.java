import java.util.Iterator;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

public class Example12RequesterAgent extends Agent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void setup() {
		addBehaviour(new TickerBehaviour(this, 2500) {
			private static final long serialVersionUID = 1L;

			@SuppressWarnings("rawtypes")
			public void onTick() {
				try {
					System.out.println("searching for agents...");

					ServiceDescription filter = new ServiceDescription();
					filter.setType("service1");
					DFAgentDescription dfd = new DFAgentDescription();
					dfd.addServices(filter);

					DFAgentDescription[] results = DFService.search(myAgent, dfd);
					for (int i = 0; i < results.length; ++i) {
						System.out.println(results[i].getName().getLocalName() + ":");
						Iterator it = results[i].getAllServices();
						while (it.hasNext()) {
							ServiceDescription sd = (ServiceDescription) it.next();
							System.out.println("   -  " + sd.getName());
						}
					}
					System.out.println();
				} catch (Exception e) {

				}
			}
		});
	}

}
