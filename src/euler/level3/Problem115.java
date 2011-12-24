package euler.level3;

import euler.Problem;
import euler.combination.BinaryCombinationsWithLimit;

public class Problem115 extends Problem<Integer> {

    private static final int LIMIT = 1000000;

    @Override
    public Integer solve() {
        for (int n = 50;; n++) {
            if (new BinaryCombinationsWithLimit(n, 50).getTotalOptions() > LIMIT) {
                return n;
            }
        }
    }

}
