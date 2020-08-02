package euler.level3;

import euler.IntegerProblem;
import euler.sequence.Primes;

public class Problem123 extends IntegerProblem {
    private static final long MIN = 10000000000l;

    @Override
    public long solve() {
        Primes primes = new Primes();
        long prime = primes.next();
        for (int n = 3;; n += 2) {
            primes.next();
            prime = primes.next();
            if (2 * (n + 1) * prime > MIN) {
                return n;
            }
        }
    }
}
