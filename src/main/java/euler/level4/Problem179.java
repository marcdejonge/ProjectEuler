package euler.level4;

import euler.IntegerProblem;

public class Problem179 extends IntegerProblem {
    private static final int LIMIT = 10000000;

    @Override
    public long solve() {
        int[] nrDivisors = new int[LIMIT];
        for (int div = 2; div < LIMIT; div++) {
            for (int nr = div; nr < LIMIT; nr += div) {
                nrDivisors[nr]++;
            }
        }

        int count = 0;
        for (int n = 1; n < LIMIT - 1; n++) {
            if (nrDivisors[n] == nrDivisors[n + 1]) {
                count++;
            }
        }
        return count;
    }
}
