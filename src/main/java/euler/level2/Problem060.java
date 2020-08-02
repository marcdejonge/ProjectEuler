package euler.level2;

import euler.IntegerProblem;
import euler.numberic.BitSet;
import euler.sequence.Primes;

public class Problem060 extends IntegerProblem {
    private static boolean check(final int p1, final int p2) {
        long mult1 = 10;
        while (p2 > mult1) {
            mult1 *= 10;
        }

        long mult2 = 10;
        while (p1 > mult2) {
            mult2 *= 10;
        }

        return Primes.isPrime(p1 * mult1 + p2) && Primes.isPrime(p1 + p2 * mult2);
    }

    private final int[] primes;

    private final BitSet[] pairs;

    public Problem060() {
        this(2000);
    }

    public Problem060(int size) {
        primes = new int[size];
        int ix = 0;
        for (long p : new Primes()) {
            if (ix >= primes.length) {
                break;
            }
            primes[ix++] = (int) p;
        }
        pairs = new BitSet[size];
    }

    private BitSet getPair(int primeIx) {
        if (pairs[primeIx] != null) {
            return pairs[primeIx];
        }

        int p1 = primes[primeIx];
        BitSet bs = new BitSet(primeIx);
        for (int p2Ix = 0; p2Ix < primeIx; p2Ix++) {
            int p2 = primes[p2Ix];
            if (check(p1, p2)) {
                bs.set(p2Ix);
            }
        }
        pairs[primeIx] = bs;
        return bs;
    }

    @Override
    public long solve() {
        BitSet bs = new BitSet(primes.length);
        bs.flip();
        return solve(bs, 0, 0);
    }

    private Integer solve(BitSet bs, int sum, int count) {
        if (count == 5) {
            return sum;
        } else {
            for (int ix : bs) {
                BitSet nextBs = getPair(ix).and(bs);
                Integer result = solve(nextBs, sum + primes[ix], count + 1);
                if (result != null) {
                    return result;
                }
            }
            return null;
        }
    }
}
