package euler.level1;

import euler.IntegerProblem;
import euler.sequence.Primes;

public class Problem010 extends IntegerProblem {
    @Override
    public long solve() {
        final Primes p = new Primes();
        long sum = 0;
        for (long prime = p.next(); prime < 2000000; prime = p.next()) {
            sum += prime;
        }
        return sum;
    }
}
