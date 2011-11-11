package euler.sequence;

import java.util.Arrays;

public class AmicableNumbers extends AbstractSequence {
	private long[] futureNrs;

	private long n;

	private int pos, filled;

	@Override
	public void reset() {
		pos = 0;
		n = 1;
		futureNrs = new long[10];
		filled = 0;
	}

	@Override
	public long current() {
		return n;
	}

	@Override
	public long next() {
		pos++;
		for (n = n + 1;; n++) {
			if (filled > 0 && n == futureNrs[0]) {
				return pop();
			} else {
				long pair = Primes.sumOfDivisors(n) - n;
				if (pair != n) {
					long ppair = Primes.sumOfDivisors(pair) - pair;
					if (ppair == n) {
						if (pair > n) {
							saveNumber(pair);
						}
						return n;
					}
				}
			}
		}
	}

	private long pop() {
		filled--;
		long res = futureNrs[0];
		for (int i = 0; i < filled; i++) {
			futureNrs[i + 1] = futureNrs[i];
		}
		return res;
	}

	@Override
	public int position() {
		return pos;
	}

	private void saveNumber(long value) {
		int ix = Arrays.binarySearch(futureNrs, 0, filled, value);
		if (ix < 0) {
			if (filled == futureNrs.length) {
				// Double the size!
				long[] temp = new long[filled * 2];
				System.arraycopy(futureNrs, 0, temp, 0, filled);
				futureNrs = temp;
			}
			ix = -ix - 1;
			for (int jx = ix; jx < filled; jx++) {
				futureNrs[jx + 1] = futureNrs[jx];
			}
			futureNrs[ix] = value;
			filled++;
		}
	}
}
