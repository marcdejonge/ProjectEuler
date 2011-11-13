package euler.level2;

import euler.Problem;
import euler.numberic.BitSet;
import euler.sequence.Primes;

public class Problem087 extends Problem<Integer> {

	@Override
	public Integer solve() {
		final int MAX = 50000000;
		BitSet bs = new BitSet(MAX);
		int[] primes = new Primes().head((int)Math.sqrt(MAX));

		int size = primes.length;
		long[] x2 = new long[size];
		long[] x3 = new long[size];
		long[] x4 = new long[size];

		int ix = 0;
		for (int x : primes) {
			if (x < Math.sqrt(MAX)) {
				x4[ix] = x * (x3[ix] = x * (x2[ix] = x * x));
				ix++;
			} else {
				break;
			}
		}

		for (int i2 = 0; i2 < size; i2++) {
			for (int i3 = 0; i3 < size; i3++) {
				long x23 = x2[i2] + x3[i3];
				if (x23 > MAX) {
					break;
				}

				for (int i4 = 0; i4 < size; i4++) {
					long x234 = x23 + x4[i4];
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
