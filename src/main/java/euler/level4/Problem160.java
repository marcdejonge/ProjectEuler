package euler.level4;

import euler.IntegerProblem;

public class Problem160 extends IntegerProblem {
    private static final int MOD = 100000;
    private static final int UNTIL = 2560000;

    @Override
    public long solve() {
        long value = 1;
        for (int i = 1; i <= UNTIL; i++) {
            int m = i;
            while (m % 5 == 0) {
                m /= 5;
                value /= 2;
            }
            value = value * m % (MOD * 10000);
        }
        return (int) (value % MOD);
    }
}
