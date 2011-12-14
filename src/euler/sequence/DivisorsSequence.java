package euler.sequence;

import java.util.Arrays;

public class DivisorsSequence extends AbstractSequence {
    private static final Primes tempPrimes = new Primes();

    private static final long[] tempDivisors = new long[64];

    private static final int[] tempIDivisors = new int[64];

    public static synchronized int[] divisors(int nr) {
        tempPrimes.reset();
        int ix = 0;
        for (long prime : tempPrimes) {
            while (nr % prime == 0) {
                nr /= prime;
                tempIDivisors[ix++] = (int) prime;
            }
            if (nr == 1) {
                break;
            }
        }
        return Arrays.copyOf(tempIDivisors, ix);
    }

    public static synchronized long[] divisors(long nr) {
        tempPrimes.reset();
        int ix = 0;
        for (long prime : tempPrimes) {
            while (nr % prime == 0) {
                nr /= prime;
                tempDivisors[ix++] = prime;
            }
            if (nr == 1) {
                break;
            }
        }
        return Arrays.copyOf(tempDivisors, ix);
    }

    private Primes primes;
    private long nr, last;
    private int pos;

    public DivisorsSequence(long nr) {
        this.nr = nr;
        primes = new Primes();
        primes.next();
        last = 0;
        pos = 0;
    }

    @Override
    public long current() {
        return last;
    }

    @Override
    public long next() {
        for (long prime = primes.current(); nr > 1; prime = primes.next()) {
            if (nr % prime == 0) {
                nr /= prime;
                last = prime;
                pos++;
                return prime;
            }
        }
        return 1;
    }

    @Override
    public long position() {
        return pos;
    }

    @Override
    public void reset() {
        primes = new Primes();
        primes.next();
        last = 0;
        pos = 0;
    }
}
