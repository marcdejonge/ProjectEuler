package euler.level2;

import euler.IntegerProblem;
import euler.numberic.BitSet;
import euler.sequence.Primes;

public class Problem087 extends IntegerProblem {

    @Override
    public long solve() {
        final int MAX = 50000000;
        final BitSet bs = new BitSet(MAX);
        final int[] primes = new Primes().head((int) Math.sqrt(MAX));

        final int size = primes.length;
        final long[] x2 = new long[size];
        final long[] x3 = new long[size];
        final long[] x4 = new long[size];

        int ix = 0;
        for (final int x : primes) {
            if (x < Math.sqrt(MAX)) {
                x4[ix] = x * (x3[ix] = x * (x2[ix] = x * x));
                ix++;
            } else {
                break;
            }
        }

        for (int i2 = 0; i2 < size; i2++) {
            for (int i3 = 0; i3 < size; i3++) {
                final long x23 = x2[i2] + x3[i3];
                if (x23 > MAX) {
                    break;
                }

                for (int i4 = 0; i4 < size; i4++) {
                    final long x234 = x23 + x4[i4];
                    if (x234 > MAX) {
                        break;
                    }

                    bs.set((int) x234);
                }
            }
        }

        return bs.cardinality();
    }
}
