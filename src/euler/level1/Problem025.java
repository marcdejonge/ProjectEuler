package euler.level1;

import java.math.BigInteger;

import euler.Problem;

public class Problem025 extends Problem<Integer> {
	@Override
	public Integer solve() {
		BigInteger f1 = BigInteger.ONE, f2 = BigInteger.ONE;
		BigInteger res = f1.add(f2);
		BigInteger min = BigInteger.TEN.pow(999);
		int term = 3;
		while (res.compareTo(min) < 0) {
			f1 = f2;
			f2 = res;
			res = f1.add(f2);
			term++;
		}
		return term;
	}
}
