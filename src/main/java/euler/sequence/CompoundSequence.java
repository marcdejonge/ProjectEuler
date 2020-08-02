package euler.sequence;

public class CompoundSequence extends AbstractSequence {
    private static final int COUNT_SHIFT = 26;
    private static final int COUNT_MASK = -1 << COUNT_SHIFT;
    private static final int COUNT_INCR = 1 << COUNT_SHIFT;
    private static final int PRIME_MASK = COUNT_INCR - 1;

    private final int limit;
    private final int[] primes;
    private final int[] selectedPrimes;
    private int ix;
    private long nr, pos;

    public CompoundSequence(int limit) {
        this.limit = limit;
        primes = new Primes().head(limit);
        selectedPrimes = new int[32];
        ix = 0;
        nr = 1;
    }

    public int countSelectedPrimes() {
        return ix + 1;
    }

    @Override
    public long current() {
        return nr;
    }

    public long getNr() {
        return nr;
    }

    public int getSelectedPrime(int ix) {
        return primes[selectedPrimes[ix] & PRIME_MASK];
    }

    public int getSelectedPrimeCount(int ix) {
        return (selectedPrimes[ix] & COUNT_MASK) >> COUNT_SHIFT;
    }

    @Override
    public long next() {
        pos++;
        int currPrimeIx = selectedPrimes[ix] & PRIME_MASK;

        // First we try to increase the current last prime. If that works, return that number.
        int prime = primes[currPrimeIx];
        nr *= prime;
        if (nr < limit) {
            selectedPrimes[ix] += COUNT_INCR;
            return nr;
        }
        nr /= prime;

        while (ix >= 0) {
            currPrimeIx = selectedPrimes[ix] & PRIME_MASK;
            prime = primes[currPrimeIx];

            // So that won't work anymore. Then decrease the this prime
            nr /= prime;
            selectedPrimes[ix] -= COUNT_INCR;

            if (currPrimeIx + 1 < primes.length) {
                // And add a new prime after this one that is just one higher, or replace the current one if it is zero
                prime = primes[++currPrimeIx];
                if (getSelectedPrimeCount(ix) != 0) {
                    ix++;
                }
                selectedPrimes[ix] = COUNT_INCR | currPrimeIx;
                nr *= prime;

                if (nr < limit) {
                    return nr;
                }
            }

            // Failed to add a new next prime number, so we drop the current ix
            nr /= prime;
            ix--;
        }

        return 0;
    }

    @Override
    public long position() {
        return pos;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int ix = 0; ix <= this.ix; ix++) {
            sb.append(getSelectedPrime(ix)).append('^').append(getSelectedPrimeCount(ix)).append(" * ");
        }
        if (sb.length() >= 3) {
            sb.setLength(sb.length() - 3);
        }
        sb.append(" = ").append(nr);
        return sb.toString();
    }
}