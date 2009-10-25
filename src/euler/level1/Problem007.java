package euler.level1;

import euler.Problem;
import euler.sequence.Primes;

public class Problem007 extends Problem<Long> {
	@Override
	public Long solve() {
		return new Primes().get(10001);
	}
}
