package euler.level2;

import euler.Problem;

public class Problem053 extends Problem<Integer> {
	private long calc(int n, int r) {
		long total = 1;
		for (int x = 0; x < r; x++) {
			total *= n - x;
		}
		for (int x = 1; x <= r; x++) {
			total /= x;
		}
		return total;
	}

	@Override
	public Integer solve() {
		int total = 0;
		int n = 2, r = 1;
		while (n <= 100) {
			if (calc(n, r) > 1000000) {
				while (calc(n, r - 1) > 1000000) {
					r--;
				}
				total += 2 * (n / 2 - r + 1) - (n & 1);
				n++;
			} else if (r < n / 2) {
				r++;
			} else {
				n++;
			}
		}
		return total;
	}
}
