package Echo;

import java.util.Random;
import java.util.Set;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class EchoAlgorithm {
	public static final int NODES = 8;
	public static Node[] nodes = new Node[NODES];

	public static void main(String args[]) {
		createNodes();
	}

	private static void createNodes() {
		CyclicBarrier barrier = new CyclicBarrier(NODES, new Runnable() {
			public void run() {
				System.out.println("All nodes have reached the cyclic barrier. System is shutting down...");
				System.exit(0);
			}
		});

		for (int i = 0; i < NODES; i++) {
			nodes[i] = new NodeAbstract("Node_" + (i + 1), i == 0 ? true : false, barrier) {

				@Override
				public void run() {
					try {
						System.out.println(this.toString() + " is now going to sleep...");
						synchronized (this) {
							while (!this.isAwake()) {
								this.wait();
							}
							Thread.sleep(200);
						}
						for (Node neighbour : this.getNeighbours()) {
							this.wakeup(neighbour);
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				@Override
				public void wakeup(Node neighbour) {
					try {
						synchronized (neighbour) {
							if (!neighbour.isAwake()) {
								System.out.println(this.toString() + ": WAKE UP, " + neighbour.toString() + " !!!");
								neighbour.setAwake(true);
								neighbour.notify();
							}
						}
						// synchronized (this) {
						//
						// }
						this.incrN();
						if (this.getNeighbours().size() == 1 && !this.isInitiator()) {
							this.echo(neighbour, "ECHO");
							this.getBarrier().await();
						} else if (this.getN() == this.getNeighbours().size() && !this.isInitiator()) {
							System.out.println(this.toString() + ": All my neighbours are awake.");
							// if (!this.isInitiator()) {
							// for (Node node : this.getNeighbours()) {
							// if (!neighbour.toString().equals(node.toString())) {
							// this.echo(node, "ECHO");
							// }
							// }
							// }
							for (Node node : this.getNeighbours()) {
								this.echo(node, "ECHO");

							}
							System.out.println(this.toString() + " is now waiting at the barrier.");
							this.getBarrier().await();
						} else if (this.getN() == this.getNeighbours().size() && this.isInitiator()) {
							while (!(this.getN() == this.getNeighbours().size() * 2)) {
								synchronized (this) {
									this.wait();

								}
							}
							System.out.println("Echos have reached the initiator.");
							this.getBarrier().await();
						}

					} catch (Exception e) {
						e.printStackTrace();
					}

				}

				@Override
				public void hello(Node neighbour) {
					System.out.println(this.toString() + ": Hello, " + neighbour.toString());
				}

				@Override
				public void echo(Node neighbour, Object data) {
					try {
						if (this != neighbour) {

						}

						synchronized (neighbour) {
							neighbour.incrN();
							neighbour.notify();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				@Override
				public void setupNeighbours(Node... neighbours) {
					for (int i = 0; i < neighbours.length; i++) {
						this.getNeighbours().add(neighbours[i]);
						neighbours[i].getNeighbours().add(this);
					}
				}

				@Override
				public Set<Node> getNeighbours() {
					return this.neighbours;
				}

				@Override
				public CyclicBarrier getBarrier() {
					return this.barrier;
				}

				@Override
				public String toString() {
					return this.name;
				}

				@Override
				public void startAsThread() {
					new Thread(this).start();
				}

				@Override
				public int getN() {
					return this.n;
				}

				@Override
				public void incrN() {
					this.n++;
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
				public boolean isInitiator() {
					return this.initiator;
				}
			};
		}

		createRandomNeighboursForNodes();
	}

	private static void createRandomNeighboursForNodes() {
		for (int i = 0; i < nodes.length; i++) {
			int numberOfNeighbours = new Random().nextInt(2) + 1;
			Node[] neighbours = new Node[numberOfNeighbours];
			for (int j = 0; j < numberOfNeighbours; j++) {
				int index = new Random().nextInt(NODES);
				while (nodes[i].getNeighbours().contains(nodes[index]) || (index == i && numberOfNeighbours == 1)) {
					index = new Random().nextInt(NODES);
				}
				neighbours[j] = nodes[index];
			}
			nodes[i].setupNeighbours(neighbours);
		}
		printNodesAndNeighbours();
	}

	private static void printNodesAndNeighbours() {
		for (int i = 0; i < nodes.length; i++) {
			Object[] neighbours = nodes[i].getNeighbours().toArray();
			System.out.print(nodes[i].toString() + "'s neighbourhood: ");
			for (int j = 0; j < neighbours.length; j++) {
				System.out.print(neighbours[j] + (j == neighbours.length - 1 ? "" : ", "));
			}
			System.out.println();
		}

		welcomeNeighbourhood();
	}

	private static void welcomeNeighbourhood() {
		System.out.println("________________________________________\n");
		for (int i = 0; i < NODES; i++) {
			for (Node neighbour : nodes[i].getNeighbours()) {
				nodes[i].hello(neighbour);
			}
		}
		System.out.println("________________________________________\n");

		goToSleep();
	}

	private static void goToSleep() {
		for (int i = 0; i < NODES; i++) {
			nodes[i].startAsThread();
		}

		startEchoAlgorithm();
	}

	private static void startEchoAlgorithm() {
		synchronized (nodes[0]) {
			try {
				Thread.sleep(1000);

				System.out.println("________________________________________\n");
				System.out.println("Starting Echo Algorithm with initiator: " + nodes[0].toString());
				nodes[0].setAwake(true);
				nodes[0].notify();

			} catch (InterruptedException e) {
			}
		}

	}

}
