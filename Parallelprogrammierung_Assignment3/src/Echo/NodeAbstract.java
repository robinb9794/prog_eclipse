package Echo;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CyclicBarrier;

/**
 * Abstract implementation of the Node interface.
 */
public abstract class NodeAbstract extends Thread implements Node {

	/** Name of this node */
	protected final String name;

	/** Is this node the initiator of the echo algorithm? */
	protected final boolean initiator;

	/**
	 * Collection of known neighbours of this node; only the methods of the
	 * neighbours in this collection can be called.
	 */
	protected final Set<Node> neighbours = new HashSet<Node>();

	/** CyclicBarrier to synchronize threads after calls of hello method */
	protected final CyclicBarrier barrier;

	protected int n;

	protected boolean awake;

	/** Abstract constructor of a node */
	public NodeAbstract(String name, boolean initiator, CyclicBarrier barrier) {
		super(name);
		this.name = name;
		this.initiator = initiator;
		this.barrier = barrier;
		this.n = 0;
		this.awake = false;
	}

}
