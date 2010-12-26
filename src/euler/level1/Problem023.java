package euler.level1;

import euler.Problem;
import euler.numberic.BitSet;
import euler.sequence.AbundantNumbers;

public class Problem023 extends Problem<Integer> {

	@Override
	public Integer solve() {
		final int MAX = 28124;

		int[] abNrs = new AbundantNumbers().head(MAX);

		BitSet bs = new BitSet(MAX);
		for (int i = 0; i < abNrs.length; i++) {
			for (int j = 0; j <= i; j++) {
				int sum = (int) (abNrs[i] + abNrs[j]);
				if (sum > MAX) {
					break;
				}
				bs.set(sum);
			}
		}

		int sum = 0;
		for (int i = 1; i < MAX; i++) {
			if (!bs.isSet(i)) {
				sum += i;
			}
		}
		return sum;
	}

}
