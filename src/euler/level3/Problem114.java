package euler.level3;

import euler.Problem;
import euler.combination.BinaryCombinationsWithLimit;

public class Problem114 extends Problem<Long> {
    @Override
    public Long solve() {
        return new BinaryCombinationsWithLimit(50, 3).getTotalOptions();
    }
}
