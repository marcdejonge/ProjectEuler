package euler.level5;

import euler.MultiCoreProblem;
import euler.sequence.NaturalNumbers;

public class Problem206 extends MultiCoreProblem {

	public Problem206() {
		super(new NaturalNumbers((long) Math.sqrt(1020304050607080900L)), 10000);
	}

	private final long[] arr = new long[] { 0, 9, 8, 7, 6, 5, 4, 3, 2, 1 };

	@Override
	public boolean handleNumber(long nr) {
		if (nr > (long) Math.sqrt(1929394959697989990L))
			return false;

		long square = nr * nr;
		for (long a : arr) {
			if (square % 10 != a) {
				return result.get() == 0;
			}
			square /= 100;
		}

		result.set(nr);
		return false;
	}

}
