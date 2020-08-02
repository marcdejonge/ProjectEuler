package euler.level2;

import euler.IntegerProblem;

public class Problem072 extends IntegerProblem {
    @Override
    public long solve() {
        final int MAX = 1000000;

        final int[] values = new int[MAX + 1];
        for (int i = 2; i <= MAX; i++) {
            values[i] = (i & 1) == 0 ? i / 2 : i;
        }

        long total = 1;
        for (int x = 3; x <= MAX; x++) {
            if (values[x] == x) {
                for (int i = x; i <= MAX; i += x) {
                    values[i] -= values[i] / x;
                }
            }
            total += values[x];
        }

        return total;
    }
}
