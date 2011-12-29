package euler.level3;

import euler.Problem;
import euler.combination.CombinationGenerator;

public class Problem121 extends Problem<Long> {

    private final int n;

    public Problem121() {
        this(15);
    }

    public Problem121(int n) {
        this.n = n;
    }

    public long fact(int n) {
        if (n <= 1) {
            return 1;
        } else {
            return n * fact(n - 1);
        }
    }

    public long mult(Integer[] values) {
        long mult = 1;
        for (Integer x : values) {
            mult *= x;
        }
        return mult;
    }

    @Override
    public Long solve() {
        long sum = 1;

        Integer[] options = new Integer[n];
        for (int ix = 0; ix < options.length; ix++) {
            options[ix] = ix + 1;
        }

        for (int selected = 1; selected < (n + 1) / 2; selected++) {
            for (Integer[] selection : new CombinationGenerator<Integer>(options, selected)) {
                sum += mult(selection);
            }
        }

        return fact(n + 1) / sum;
    }

}
