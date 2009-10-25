package euler.level1;

import euler.Problem;

public class Problem034 extends Problem<Integer> {

	@Override
	public Integer solve() {
		int sum = 0;

		int[] facts = new int[] {
				1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880
		};

		int[] nrs = new int[] {
				0, 0, 0, 0, 0, 0, 3
		};
		int value = 3;

		while (value < 10000000) {
			int total = 0;
			for (int i = 0; i < nrs.length; i++) {
				if (!(nrs[i] == 0 && total == 0)) {
					total += facts[nrs[i]];
				}
			}
			if (total == value) {
				sum += value;
			}

			value++;
			for (int i = nrs.length - 1; i >= 0; i--) {
				nrs[i]++;
				if (nrs[i] >= 10) {
					nrs[i] = 0;
				} else {
					break;
				}
			}
		}
		return sum;
	}

}
