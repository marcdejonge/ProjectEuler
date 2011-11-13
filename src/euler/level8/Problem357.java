package euler.level8;

import euler.MultiCoreProblem;
import euler.sequence.Primes;

public class Problem357 extends MultiCoreProblem {

	public static final long LIMIT = 100000000l;

	public Problem357() {
		super(new Primes(), 10000);
	}

	@Override
	public boolean handleNumber(long prime) {
		if (prime >= LIMIT) {
			return false;
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
			result.addAndGet(n);
		}
		return true;
	}
}
