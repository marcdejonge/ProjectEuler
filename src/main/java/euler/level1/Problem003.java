package euler.level1;

import euler.IntegerProblem;

public class Problem003 extends IntegerProblem {

    @Override
    public long solve() {
        long value = 600851475143l;

        int divider = 1;
        do {
            divider += 2;
            while (value % divider == 0) {
                value /= divider;
            }
        } while (value > 1);

        return divider;
    }
}