package euler.level2;

import euler.Problem;
import euler.sequence.ContinuedFraction;
import euler.sequence.SimpleSequence;

public class Problem065 extends Problem<Integer> {
	@Override
	public Integer solve() {
		SimpleSequence seq = new SimpleSequence() {
			@Override
			public long current() {
				if (getN() == 1) {
					return 2;
				} else if (getN() % 3 == 0) {
					return (getN() / 3) * 2;
				} else {
					return 1;
				}
			}
		};

		ContinuedFraction fractions = new ContinuedFraction(seq);
		return fractions.get(100).getFirst().digitalSum();
	}
}
