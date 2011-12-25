package euler.level3;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

import euler.MultiCoreProblem;
import euler.sequence.Primes;

public class Problem124 extends MultiCoreProblem {

    private static class Rad extends AtomicInteger implements Comparable<Rad> {
        private static final long serialVersionUID = -3625315145491534426L;

        private final int n;

        public Rad(int n) {
            super(1);
            this.n = n;
        }

        public void addPrime(int p) {
            int rad = get();
            compareAndSet(rad, rad * p);
        }

        @Override
        public int compareTo(Rad o) {
            return get() - o.get();
        }

        public int getN() {
            return n;
        }

        @Override
        public String toString() {
            return String.format("%d -> %d", n, get());
        }
    }

    private final Rad[] rads;
    private final int k;

    public Problem124() {
        this(100000, 10000);
    }

    public Problem124(int length, int k) {
        super(new Primes(), 100);
        rads = new Rad[length];
        for (int ix = 0; ix < length; ix++) {
            rads[ix] = new Rad(ix + 1);
        }
        this.k = k;
    }

    @Override
    public void finished() {
        Arrays.sort(rads);
        result.set(rads[k - 1].getN());
    }

    @Override
    public boolean handleNumber(long nr) {
        if (nr > rads.length) {
            return false;
        }
        int p = (int) nr;
        for (int ix = p; ix <= rads.length; ix += p) {
            rads[ix - 1].addPrime(p);
        }
        return true;
    }

}
