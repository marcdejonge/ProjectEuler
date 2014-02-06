package euler.level3;

import euler.IntegerProblem;
import euler.combination.BinaryCombinationsWithLimit;

public class Problem115 extends IntegerProblem {

    private static final int LIMIT = 1000000;

    @Override
    public long solve() {
        for (int n = 50;; n++) {
            if (new BinaryCombinationsWithLimit(n, 50).getTotalOptions() > LIMIT) {
                return n;
            }
        }
    }

}
