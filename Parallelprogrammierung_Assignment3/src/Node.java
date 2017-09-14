import java.util.Set;
import java.util.concurrent.CyclicBarrier;

/**
 * This interface defines the methods of each node participating in the echo
 * algorithm. These methods can be called by the neighbours of a node.
 */
public interface Node {

	/**
	 * Greetings from a neighbour. Before starting the echo algorithm this
	 * message is called once by every node for all its initially known
	 * neighbours.
	 * 
	 * The main purpose is that <code>this</code> node gets the chance to add
	 * the calling <code>neighbour</code> to its own list of neighbours (just in
	 * case the neighbour was previously unknown to this node although the
	 * neighbour itself knows this node).
	 * 
	 * @param neighbour 
	 */
	public void hello(Node neighbour);

	/**
	 * Incoming "wakeup" message from a neighbour.
	 * 
	 * @param neighbour
	 */
	public void wakeup(Node neighbour, Node pred);

	/**
	 * Incoming "echo" message from a neighbour. The neighbour can also send
	 * some data with this echo message. The data object might e.g. contain
	 * information about edges of the spanning tree known so far; if not needed,
	 * set data to null.
	 * 
	 * @param neighbour
	 * @param data
	 */
	public void echo(Node neighbour, Object data);

	public void addNewNeighbour(Node neighbour);

	public boolean isAwake();

	public void setAwake(boolean awake);

	public void wakeUpNeighbours();

	public int getN();

	public void incrN();

	public boolean isInitiator();

	public void setPred(Node pred);

	public Node getPred();

	public Set<Node> getNeighbours();

	public void setN(int n);

	public CyclicBarrier getBarrier();
}
