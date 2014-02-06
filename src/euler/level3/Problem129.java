package euler.level3;

import euler.IntegerProblem;

public class Problem129 extends IntegerProblem {
    private static final int LIMIT = 1000000;

    public static int A(int n) {
        int k = 2;
        for (int x = 11 % n; x > 0; k += 2) {
            x = (100 * x + 11) % n;
        }
        return k;
    }

    @Override
    public long solve() {
        for (int n = LIMIT | 1;; n += 2) {
            if (n % 5 == 0) {
                continue;
            }

            if (A(n) >= LIMIT) {
                return n;
            }
        }
    }
}
