package Aufgabe1;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Aufgabe 1.1 c)
 * 
 * @author Robin
 *
 */

public class StrangeCounter_Atomic {
	private final static int INCREMENTERS = 50;
	private final static int RUNS = 500;

	protected static class MyLongAtomic implements CounterInterface {
		private AtomicLong counter = new AtomicLong();

		@Override
		public long get() {
			return counter.get();
		}

		@Override
		public long incrementAndGet() {
			return counter.incrementAndGet();
		}

		@Override
		public void check(long desired) {
			if (desired != get()) {
				System.out.println("Die Threads sind sich leider in die Quere gekommen.");
			}
		}

	}

	protected static class Incrementer implements Runnable {
		private final CountDownLatch start, end;
		static CounterInterface counter;

		public Incrementer(CountDownLatch start, CountDownLatch end, CounterInterface counter) {
			this.start = start;
			this.end = end;
			this.counter = counter;
		}

		public void run() {
			try {
				start.await();
				for (int i = 0; i < RUNS; i++) {
					counter.incrementAndGet();
				}
				end.countDown();
			} catch (Exception e) {

			}
		}

		public static void main(String args[]) {
			CountDownLatch startLatch = new CountDownLatch(1);
			CountDownLatch endLatch = new CountDownLatch(INCREMENTERS);

			/**
			 * direkte Threads
			 */

			// Thread[] Incrementers = new Thread[INCREMENTERS];
			// for (int i = 0; i < INCREMENTERS; i++) {
			// Incrementers[i] = new Thread(new Incrementer(startLatch,
			// endLatch, new MyLongAtomic()));
			// Incrementers[i].start();
			// }

			/**
			 * ExecutorServices
			 */

			// ExecutorService es = Executors.newFixedThreadPool(INCREMENTERS);
			// ExecutorService es = Executors.newCachedThreadPool();
			// ExecutorService es =
			// Executors.newScheduledThreadPool(INCREMENTERS);
			ExecutorService es = Executors.newSingleThreadExecutor();

			for (int i = 0; i < INCREMENTERS; i++) {
				es.submit(new Incrementer(startLatch, endLatch, new MyLongAtomic()));
			}

			try {
				System.out.println("Starting with counter = " + counter.get());
				startLatch.countDown();
				endLatch.await();
				long totalInc = RUNS * INCREMENTERS;
				System.out.println("Finished after " + totalInc + " increments with counter = " + counter.get());
				counter.check(totalInc);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
