package euler.sequence;

import java.util.Arrays;

public class DivisorsSequence extends AbstractSequence {
    private static final Primes tempPrimes = new Primes();

    private static final long[] tempDivisors = new long[1024];

    private static final int[] tempIDivisors = new int[1024];

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

    public static synchronized int[] properDivisors(int nr) {
        int ix = 0;
        tempIDivisors[ix++] = 1;
        for (int div = 2; div * div <= nr; div++) {
            if (nr % div == 0) {
                tempIDivisors[ix++] = div;
                if (div * div != nr) {
                    tempIDivisors[ix++] = nr / div;
                }
            }
        }
        return Arrays.copyOf(tempIDivisors, ix);
    }

    public static synchronized int sumOfDivisors(int nr) {
        tempPrimes.reset();
        int sum = 1;
        for (long prime : tempPrimes) {
            int p = (int) prime;

            if (p * p > nr) {
                sum *= nr + 1;
            }

            int lastsum = sum;
            while (nr % p == 0) {
                nr /= p;
                sum = sum * p + lastsum;
            }
        }
        return sum;
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
