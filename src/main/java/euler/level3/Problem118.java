package euler.level3;

import euler.IntegerProblem;
import euler.sequence.LineSequence;
import euler.sequence.Primes;

public class Problem118 extends IntegerProblem {
    private int nrPrimeSets(final byte[] current, final int min, final int startIx) {
        if (startIx >= current.length) {
            return 1;
        }

        int count = 0;
        int ix = startIx;
        int nr = 0;

        while (ix < current.length) {
            nr = nr * 10 + current[ix++];
            if (nr > min && Primes.isPrime(nr)) {
                count += nrPrimeSets(current, nr, ix);
            }
        }

        return count;
    }

    @Override
    public long solve() {
        final LineSequence seq = new LineSequence(new byte[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 });
        int count = 0;
        while (seq.next()) {
            byte[] bs = seq.current();
            if ((bs[8] & 1) != 0 & bs[8] != 5) { // Skip any even-ending and 5-ending lines
                count += nrPrimeSets(bs, 0, 0);
            }
        }
        return count;
    }
}
