package euler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

import euler.sequence.AbstractSequence;

public abstract class MultiCoreProblem extends Problem<Long> {

	final AbstractSequence sequence;
	final ReentrantLock lock;
	final ForkJoinPool pool;
	protected final AtomicLong result;

	public MultiCoreProblem(AbstractSequence sequence) {
		this.sequence = sequence;
		this.lock = new ReentrantLock();
		this.pool = new ForkJoinPool();
		this.result = new AtomicLong(0);
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
					while (true) {
						lock.lock();
						final long nr = sequence.next();
						lock.unlock();
						
						if (!handleNumber(nr)) {
							break;
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
