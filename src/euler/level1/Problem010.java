package euler.level1;

import euler.Problem;
import euler.sequence.Primes;


public class Problem010 extends Problem<Long> {
	@Override
	public Long solve() {
		Primes p = new Primes();
		long sum = 0;
		for (long prime = p.next(); prime < 2000000; prime = p.next()) {
			sum += prime;
		}
		return sum;
	}
}
