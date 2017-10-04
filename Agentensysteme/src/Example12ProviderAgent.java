import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

public class Example12ProviderAgent extends Agent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void setup() {
		try {
			Object[] args = getArguments();
			if (args != null) {
				DFAgentDescription dfd = new DFAgentDescription(); // beschreibt
																	// einen
																	// Agenten
																	// und
																	// enthält
																	// eine
																	// Liste
																	// seiner
																	// angebotenen
																	// Dienste
				dfd.setName(this.getAID());

				String[] services = ((String) (args[0])).split(";");
				for (int i = 0; i < services.length; ++i) {
					ServiceDescription sd = new ServiceDescription(); // beschreibt
																		// einen
																		// konkreten
																		// Dienst
					sd.setType(services[i].trim());
					sd.setName(services[i].trim());
					dfd.addServices(sd);
				}

				DFService.register(this, dfd); // kapselt den Zugri auf die
												// Yellow Pages und gestattet
												// das Registrieren von Agenten
												// und Diensten mit register ()
			} else {
				doDelete();
			}
		} catch (Exception e) {

		}
	}

	protected void takeDown() {
		try {
			DFAgentDescription dfd = new DFAgentDescription();
			dfd.setName(getAID());
			DFService.deregister(this, dfd);
		} catch (Exception e) {

		}
	}

}
