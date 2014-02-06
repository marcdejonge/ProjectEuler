package euler.level2;

import euler.IntegerProblem;

public class Problem086 extends IntegerProblem {
    private final static int MAX = 1000000;

    private boolean isSquare(int n) {
        double d = Math.sqrt(n);
        return d == (long) d;
    }

    @Override
    public long solve() {
        int count = 0;

        int a;
        for (a = 1; count <= MAX; a++) {
            for (int b = 2; b <= 2 * a; b++) {
                if (isSquare(a * a + b * b)) {
                    // Now count up the number of combinations such that 1 <= x <= y <= b && x + y = a
                    if (b >= a) {
                        count += a - (b - 1) / 2;
                    } else {
                        count += b / 2;
                    }
                }
            }
        }
        return a - 1;
    }
}
