package euler.level1;

import euler.IntegerProblem;
import euler.sequence.Primes;

public class Problem007 extends IntegerProblem {
    @Override
    public long solve() {
        return new Primes().get(10001);
    }
}
