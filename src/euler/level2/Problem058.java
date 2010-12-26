package euler.level2;

import euler.Problem;
import euler.sequence.Primes;

public class Problem058 extends Problem<Long> {
	@Override
	public Long solve() {
		long nrValues = 1, nrPrimes = 0;
		long currValue = 1, currAdd = 2;
		while (nrPrimes == 0 || nrValues / nrPrimes < 10) {
			for (int i = 0; i < 4; i++) {
				currValue += currAdd;
				nrValues++;
				if (Primes.isPrime(currValue)) {
					nrPrimes++;
				}
			}
			currAdd += 2;
		}
		return currAdd - 1;
	}
}
