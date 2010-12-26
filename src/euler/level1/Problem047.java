package euler.level1;

import euler.Problem;
import euler.sequence.Primes;

public class Problem047 extends Problem<Integer> {
	@Override
	public Integer solve() {
		int cnt = 0;
		for (int i = 1000;; i++) {
			if (Primes.nrOfPrimeFactors(i, true) == 4) {
				cnt++;
				if (cnt == 4) {
					return i - 3;
				}
			} else {
				cnt = 0;
			}
		}
	}
}
