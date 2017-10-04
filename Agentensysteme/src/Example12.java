import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;

public class Example12 {
	public static void main(String args[]) {
		try {
			Runtime runtime = Runtime.instance();
			Profile profile = new ProfileImpl();
			AgentContainer container = runtime.createMainContainer(profile);

			AgentController provider1 = container.createNewAgent("provider1", Example12ProviderAgent.class.getName(),
					new String[] { "service1,service2" });
			AgentController provider2 = container.createNewAgent("provider2", Example12ProviderAgent.class.getName(),
					new String[] { "service2,service3" });
			AgentController provider3 = container.createNewAgent("provider3", Example12ProviderAgent.class.getName(),
					new String[] { "service1,service2,service3" });
			AgentController requester = container.createNewAgent("requester", Example12RequesterAgent.class.getName(),
					null);
			AgentController rma = container.createNewAgent("rma", "jade.tools.rma.rma", args);

			provider1.start();
			provider2.start();
			provider3.start();
			requester.start();
			rma.start();
		} catch (Exception e) {

		}
	}
}
