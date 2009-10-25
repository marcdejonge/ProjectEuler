package euler.level1;

import euler.Problem;

public class Problem009 extends Problem<Integer> {

	@Override
	public Integer solve() {
		for (int c = 1; c < 1000; c++) {
			for (int b = 1; b < c; b++) {
				int a = 1000 - b - c;
				if (a > b || a < 0) {
					continue;
				}
				if (a * a + b * b == c * c) {
					return a * b * c;
				}
			}
		}
		return -1;
	}

}
