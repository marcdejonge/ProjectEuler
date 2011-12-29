package euler.level3;

import euler.Problem;
import euler.combination.CombinationGenerator;

public class Problem121 extends Problem<Long> {

    public static long fact(final int n) {
        if (n <= 1) {
            return 1;
        } else {
            return n * fact(n - 1);
        }
    }

    public static long mult(final Integer[] values) {
        long mult = 1;
        for (Integer x : values) {
            mult *= x;
        }
        return mult;
    }

    private final int n;

    public Problem121() {
        this(15);
    }

    public Problem121(int n) {
        this.n = n;
    }

    @Override
    public Long solve() {
        long sum = 1;

        Integer[] loseChances = new Integer[n];
        for (int ix = 0; ix < loseChances.length; ix++) {
            loseChances[ix] = ix + 1;
        }

        for (int nrLost = 1; nrLost < (n + 1) / 2; nrLost++) {
            for (Integer[] lostSelection : new CombinationGenerator<Integer>(loseChances, nrLost)) {
                sum += mult(lostSelection);
            }
        }

        return fact(n + 1) / sum;
    }

}
