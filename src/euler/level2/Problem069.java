package euler.level2;

import euler.Problem;
import euler.sequence.Primes;

public class Problem069 extends Problem<Long> {

	@Override
	public Long solve() {
		Primes primes = new Primes();
		long nr = 1;
		for(; nr < 1000000; nr *= primes.next());
		return nr / primes.current();
	}

}
