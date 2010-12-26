package euler.level1;

import euler.Problem;

public class Problem033 extends Problem<Integer> {
	@Override
	public Integer solve() {
		int totA = 1;
		int totB = 1;
		for (int a = 1; a < 10; a++) {
			for (int b = 1; b < a; b++) {
				for (int c = 1; c < 10; c++) {
					if ((c * 10 + a) * b == (b * 10 + c) * a) {
						totA *= a;
						totB *= b;
					}
				}
			}
		}
		return totA / totB;
	}
}
