package euler.level3;

import euler.Problem;

public class Problem129 extends Problem<Integer> {
    private static final int LIMIT = 1000000;

    public static int A(int n) {
        int k = 1, r = 1;
        while (r > 0) {
            r = (10 * r + 1) % n;
            k++;
        }
        return k;
    }

    @Override
    public Integer solve() {
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
