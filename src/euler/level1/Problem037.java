package euler.level1;

import euler.Problem;
import euler.sequence.Primes;

public class Problem037 extends Problem<Integer> {

	@Override
	public Integer solve() {
		Primes primes = new Primes();
		primes.get(4); // Drop first 4 primes

		int total = 0, found = 0;
		for (long prime = primes.next(); found < 11; prime = primes.next()) {
			long powerTen = 10;
			boolean allPrime = true;
			while (allPrime) {
				long left = prime % powerTen;
				long right = prime / powerTen;
				if (right == 0) {
					break;
				}
				if (!Primes.isPrime(left) || !Primes.isPrime(right)) {
					allPrime = false;
				}
				powerTen *= 10;
			}

			if (allPrime) {
				total += prime;
				found++;
			}
		}
		return total;
	}

}
