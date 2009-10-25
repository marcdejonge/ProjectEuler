package euler.level1;

import euler.Problem;

public class Problem039 extends Problem<Integer> {

	@Override
	public Integer solve() {
		int[] times = new int[1000];
		for (int c = 1; c < 1000; c++) {
			for (int b = 1; b < c; b++) {
				for (int a = 1; a <= b; a++) {
					if (a + b + c < 1000 && a * a + b * b == c * c) {
						times[a + b + c]++;
					}
				}
			}
		}

		int max = 0;
		int maxNr = 0;
		for (int i = 0; i < times.length; i++) {
			if (times[i] > max) {
				max = times[i];
				maxNr = i;
			}
		}

		return maxNr;
	}

}
