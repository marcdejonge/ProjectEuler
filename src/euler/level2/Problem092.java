package euler.level2;

import euler.MultiCoreProblem;
import euler.sequence.NaturalNumbers;

public class Problem092 extends MultiCoreProblem {

	private static final int MAX = 10000000;

	public Problem092() {
		super(new NaturalNumbers(), MAX / 1000);
	}

	private static int squareOfDigits(int value) {
		int result = 0;
		while (value > 0) {
			int x = value % 10;
			result += x * x;
			value /= 10;
		}
		return result;
	}

	@Override
	public boolean handleNumber(long nr) {
		if(nr >= MAX) return false;
		int x = (int) nr;
		while(x != 1 && x != 89) {
			x = squareOfDigits(x);
		}
		if(x == 89) {
			result.incrementAndGet();
		}
		return true;
	}
}
