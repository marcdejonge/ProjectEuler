package euler.level1;

import euler.Problem;
import euler.sequence.AmicableNumbers;

public class Problem021 extends Problem<Long> {
	@Override
	public Long solve() {
		AmicableNumbers nrs = new AmicableNumbers();
		long sum = 0;
		for (long nr = nrs.next(); nr < 10000; nr = nrs.next()) {
			sum += nr;
		}
		return sum;
	}
}
