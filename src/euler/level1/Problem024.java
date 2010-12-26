package euler.level1;

import euler.Problem;
import euler.sequence.LineSequence;


public class Problem024 extends Problem<Long> {
	@Override
	public Long solve() {
		LineSequence seq = new LineSequence(new byte[] {
		                                                0, 1, 2, 3, 4, 5, 6, 7, 8, 9
		});
		for (int i = 1; i < 1000000; i++) {
			if (!seq.next()) {
				break;
			}
		}

		return seq.toNumber().longValue();
	}
}
