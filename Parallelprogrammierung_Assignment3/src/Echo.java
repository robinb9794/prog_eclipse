import java.util.Random;
import java.util.Set;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Echo {
	public static final int NODES = 7;
	public static final int MAX_NEIGHBOURS = 3;
	public static NodeAbstract[] nodes = new NodeAbstract[NODES];

	public static void main(String args[]) {
		for (int i = 0; i < NODES; i++) {
			// nodes[i] = new NodeAbstract("Node_" + (i + 1), i == 0 ? true : false, new CyclicBarrier(2, new Runnable() {
			// public void run() {
			// System.out.println("REACHED CYCLIC BARRIER");
			// }
			// })) {
			// @Override
			// public void run() {
			// for (Node node : neighbours) {
			// this.wakeup(node, this);
			// }
			// }
			//
			// @Override
			// public void wakeup(Node neighbour, Node p) {
			// if (!neighbour.isAwake()) {
			// neighbour.setAwake(true);
			// neighbour.setPred(p);
			// neighbour.setN(0);
			// System.out.println(toString() + (this.initiator ? " (INITIATOR) " : "") + ": WAKE UP " + neighbour.toString() + " !!!! " + neighbour.toString() + " is now "
			// + (this.isAwake() ? "awake." : "NOPE!"));
			// neighbour.wakeUpNeighbours();
			// }
			// this.incrN();
			// if (this.getN() == this.getNeighbours().size()) {
			// try {
			// System.out.println(toString() + " is now waiting...");
			// this.barrier.await();
			// } catch (Exception e) {
			// }
			// if (this.isInitiator()) {
			// finish();
			// } else {
			// this.getPred().echo(this, "ECHO");
			// }
			// }
			// }
			//
			// @Override
			// public void hello(Node neighbour) {
			// System.out.println(toString() + ": hello neighbour " + neighbour.toString());
			// }
			//
			// @Override
			// public void echo(Node neighbour, Object data) {
			// this.incrN();
			// System.out.println(toString() + ": receiving echo >" + data.toString() + "< from " + neighbour.toString());
			// if (this.getN() == this.getNeighbours().size()) {
			// try {
			// System.out.println(toString() + " is now waiting...");
			// this.barrier.await();
			// } catch (Exception e) {
			// }
			// if (this.isInitiator()) {
			// finish();
			// } else {
			// this.echo(this.getPred(), "ECHO");
			// }
			// }
			// }
			//
			// @Override
			// public void setupNeighbours(Node... neighbours) {
			// for (int i = 0; i < neighbours.length; i++) {
			// this.addNewNeighbour(neighbours[i]);
			// neighbours[i].addNewNeighbour(this);
			// }
			// }
			//
			// @Override
			// public void addNewNeighbour(Node neighbour) {
			// this.neighbours.add(neighbour);
			// hello(neighbour);
			// }
			//
			// @Override
			// public boolean isAwake() {
			// return this.awake;
			// }
			//
			// @Override
			// public void setAwake(boolean awake) {
			// this.awake = awake;
			// }
			//
			// @Override
			// public void wakeUpNeighbours() {
			// System.out.println("Starting new Thread for " + toString());
			// new Thread(this).start();
			// }
			//
			// @Override
			// public int getN() {
			// return this.n;
			// }
			//
			// @Override
			// public void incrN() {
			// ++this.n;
			// }
			//
			// @Override
			// public boolean isInitiator() {
			// return this.initiator;
			// }
			//
			// @Override
			// public void setPred(Node pred) {
			// this.pred = pred;
			// }
			//
			// @Override
			// public Node getPred() {
			// return this.pred;
			// }
			//
			// @Override
			// public Set<Node> getNeighbours() {
			// return this.neighbours;
			// }
			//
			// @Override
			// public void setN(int n) {
			// this.n = n;
			// }
			// };
		}

		createNodes();
	}

	private static void createNodes() {
		for (int i = 0; i < NODES; i++) {
			int numberOfNeighbours = new Random().nextInt(MAX_NEIGHBOURS - 1) + 1;

			nodes[i] = new NodeAbstract("Node_" + (i + 1), i == 0 ? true : false, new CyclicBarrier(numberOfNeighbours + 1, new Runnable() {
				public void run() {
				}
			})) {
				@Override
				public void run() {
					for (Node node : neighbours) {
						this.wakeup(node, this);
					}
				}

				@Override
				public void wakeup(Node neighbour, Node p) {
					if (!neighbour.isAwake()) {
						neighbour.setAwake(true);
						neighbour.setPred(p);
						neighbour.setN(0);
						System.out.println(toString() + (this.initiator ? " (INITIATOR) " : "") + ": WAKE UP " + neighbour.toString() + " !!!! " + neighbour.toString() + " is now "
								+ (this.isAwake() ? "awake." : "NOPE!"));
						neighbour.wakeUpNeighbours();
					}
					this.incrN();
					if (this.getN() == this.getNeighbours().size()) {
						try {
							System.out.println(toString() + " is now waiting for echos...");
							this.wait();
						} catch (Exception e) {
							e.printStackTrace();
						}
						if (this.isInitiator()) {
							finish();
						} else {
							this.getPred().echo(this, "ECHO");
						}
					}
				}

				@Override
				public void hello(Node neighbour) {
					System.out.println(toString() + ": hello neighbour " + neighbour.toString());
				}

				@Override
				public void echo(Node neighbour, Object data) {
					System.out.println(toString() + ": receiving echo >" + data.toString() + "< from " + neighbour.toString());
					this.incrN();
					if (this.getN() == this.getNeighbours().size() || (this.getNeighbours().size() == 1)) {
						if (this.isInitiator()) {
							finish();
						} else {
							this.getPred().notify();
						}
					}
				}

				@Override
				public void setupNeighbours(Node... neighbours) {
					for (int i = 0; i < neighbours.length; i++) {
						this.addNewNeighbour(neighbours[i]);
						neighbours[i].addNewNeighbour(this);
					}
				}

				@Override
				public void addNewNeighbour(Node neighbour) {
					this.neighbours.add(neighbour);
					hello(neighbour);
				}

				@Override
				public boolean isAwake() {
					return this.awake;
				}

				@Override
				public void setAwake(boolean awake) {
					this.awake = awake;
				}

				@Override
				public void wakeUpNeighbours() {
					System.out.println("Starting new Thread for " + toString());
					new Thread(this).start();
				}

				@Override
				public int getN() {
					return this.n;
				}

				@Override
				public void incrN() {
					++this.n;
				}

				@Override
				public boolean isInitiator() {
					return this.initiator;
				}

				@Override
				public void setPred(Node pred) {
					this.pred = pred;
				}

				@Override
				public Node getPred() {
					return this.pred;
				}

				@Override
				public Set<Node> getNeighbours() {
					return this.neighbours;
				}

				@Override
				public void setN(int n) {
					this.n = n;
				}

				@Override
				public CyclicBarrier getBarrier() {
					return this.barrier;
				}
			};

		}
		createRandomNeighbours();
		printNeighbours();
		startEcho();
	}

	private static void createRandomNeighbours() {
		for (int i = 0; i < nodes.length; i++) {
			int numberOfNeighbours = nodes[i].barrier.getParties();
			Node[] neighbours = new Node[numberOfNeighbours];
			for (int j = 0; j < numberOfNeighbours; j++) {
				int index = new Random().nextInt(NODES);
				while (nodeIsAlreadyInNeighbourhood(neighbours, nodes[index])) {
					index = new Random().nextInt(NODES);
				}
				neighbours[j] = nodes[index];
			}
			nodes[i].setupNeighbours(neighbours);
		}
	}

	private static boolean nodeIsAlreadyInNeighbourhood(Node[] neighbours, Node node) {
		boolean alreadyInNeighbourhood = false;
		for (int i = 0; i < neighbours.length; i++) {
			if (neighbours[i] != null) {
				if (neighbours[i].toString().equals(node.toString())) {
					alreadyInNeighbourhood = true;
				}
			}
		}
		return alreadyInNeighbourhood;
	}

	private static void startEcho() {
		nodes[0].awake = true;
		System.out.println(nodes[0].toString() + " (INITIATOR) is now awake. Starting echo algorithm...\n");
		new Thread(nodes[0]).start();
	}

	private static void printNeighbours() {
		System.out.println("________________________________________\n");
		for (int i = 0; i < nodes.length; i++) {
			Object[] neighbours = nodes[i].neighbours.toArray();
			System.out.print(nodes[i].toString() + "'s neighbourhood: ");
			for (int j = 0; j < neighbours.length; j++) {
				System.out.print(neighbours[j] + (j == neighbours.length - 1 ? "" : ", "));
			}
			System.out.println();
		}
		System.out.println("________________________________________\n");
	}

	private static void finish() {
		System.out.println("SHUTTING DOWN...");
	}
}
