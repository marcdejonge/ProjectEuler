package euler.level2;

import euler.Problem;

public class Problem071 extends Problem<Integer> {
	@Override
	public Integer solve() {
		for(int num = 1000000; num >= 2; num--) {
			int den = num * 3 / 7;
			if((num * 3) - (den * 7) == 1) {
				return den;
			}
		}
		return null;
	}
}
