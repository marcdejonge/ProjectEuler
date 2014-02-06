package euler.level3;

import euler.IntegerProblem;
import euler.combination.BinaryCombinationsWithLimit;

public class Problem114 extends IntegerProblem {
    @Override
    public long solve() {
        return new BinaryCombinationsWithLimit(50, 3).getTotalOptions();
    }
}
