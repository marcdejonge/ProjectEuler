package euler.level2;

import euler.IntegerProblem;

public class Problem073 extends IntegerProblem {
    private final static int MAX = 12000;

    private final int count(final int b, final int d) {
        if (b + d > MAX) {
            return 0;
        } else {
            return 1 + count(b, b + d) + count(b + d, d);
        }
    }

    @Override
    public long solve() {
        return count(2, 3);
    }
}
