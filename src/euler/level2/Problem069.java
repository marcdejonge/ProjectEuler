package euler.level2;

import euler.Problem;
import euler.sequence.Primes;

public class Problem069 extends Problem<Long> {
    private static final int TARGET = 1000000;

    @Override
    public Long solve() {
        final Primes primes = new Primes();
        long nr = 1;
        for (; nr < TARGET; nr *= primes.next()) {
        }
        return nr / primes.current();
    }
}
