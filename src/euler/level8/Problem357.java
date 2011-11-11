package euler.level8;

import euler.Problem;
import euler.sequence.Primes;

public class Problem357 extends Problem<Long> {

	private static final long LIMIT = 100000000l;

	@Override
	public Long solve() {
		long sum = 0;
		for (long prime : new Primes()) {
			if (prime >= LIMIT) {
				break;
			}

			int n = (int) (prime - 1);
			int d = 2;
			boolean stillValid = true;
			while (stillValid && d * d <= n) {
				int div = n / d;
				if (div * d == n && !Primes.isPrime(d + div)) {
					stillValid = false;
				}
				d++;
			}
			if (stillValid) {
				sum += n;
			}
		}

		return sum;
	}

}
