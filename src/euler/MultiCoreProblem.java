package euler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

import euler.sequence.LongSequence;

public abstract class MultiCoreProblem extends Problem<Long> {
    final LongSequence sequence;
    final ReentrantLock lock;
    protected final AtomicLong result;
    private final int blockSize;

    public MultiCoreProblem(LongSequence sequence, int blockSize) {
        this.sequence = sequence;
        lock = new ReentrantLock();
        result = new AtomicLong(0);
        this.blockSize = blockSize;
    }

    public void finished() {
    }

    public abstract boolean handleNumber(long nr);

    @Override
    public Long solve() {
        final int maxProcesses = Runtime.getRuntime().availableProcessors();
        final ExecutorService executor = Executors.newFixedThreadPool(maxProcesses);
        final List<Future<?>> futures = new ArrayList<>(maxProcesses);

        for (int ix = 0; ix < maxProcesses; ix++) {
            futures.add(executor.submit(new Runnable() {
                @Override
                public void run() {
                    final long[] block = new long[blockSize];
                    loop: while (true) {
                        lock.lock();
                        for (int ix = 0; ix < blockSize; ix++) {
                            block[ix] = sequence.next();
                        }
                        lock.unlock();

                        for (final long nr : block) {
                            if (!handleNumber(nr)) {
                                break loop;
                            }
                        }
                    }
                }
            }));
        }

        for (final Future<?> future : futures) {
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        finished();

        executor.shutdownNow();

        return result.get();
    }
}
