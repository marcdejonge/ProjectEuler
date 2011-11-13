package euler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

import euler.sequence.AbstractSequence;

public abstract class MultiCoreProblem extends Problem<Long> {
	private static final ForkJoinPool pool = new ForkJoinPool();

	final AbstractSequence sequence;
	final ReentrantLock lock;
	protected final AtomicLong result;
	private final int blockSize;

	public MultiCoreProblem(AbstractSequence sequence, int blockSize) {
		this.sequence = sequence;
		this.lock = new ReentrantLock();
		this.result = new AtomicLong(0);
		this.blockSize = blockSize;
	}

	public abstract boolean handleNumber(long nr);

	@Override
	public Long solve() {
		final int maxTasks = Runtime.getRuntime().availableProcessors();
		List<RecursiveAction> tasks = new ArrayList<RecursiveAction>(maxTasks);

		for (int ix = 0; ix < maxTasks; ix++) {
			RecursiveAction task = new RecursiveAction() {
				private static final long serialVersionUID = 1L;

				@Override
				protected void compute() {
					long[] block = new long[blockSize];
					loop: while (true) {
						lock.lock();
						for(int ix = 0; ix < blockSize; ix++) {
							block[ix] = sequence.next();
						}
						lock.unlock();
						
						for(long nr : block) {
							if (!handleNumber(nr)) {
								break loop;
							}
						}
					}
				}
			};
			pool.execute(task);
			tasks.add(task);
		}

		for (RecursiveAction task : tasks) {
			task.join();
		}

		return result.get();
	}
}
