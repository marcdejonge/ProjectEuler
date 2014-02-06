package euler.level1;

import euler.IntegerProblem;
import euler.sequence.Primes;
import euler.sequence.TriangleNumbers;

public class Problem012 extends IntegerProblem {
    @Override
    public long solve() {
        final TriangleNumbers nrs = new TriangleNumbers();
        for (long nr = nrs.next();; nr = nrs.next()) {
            if (Primes.nrOfDivisors(nr) >= 500) {
                return nr;
            }
        }
    }
}
