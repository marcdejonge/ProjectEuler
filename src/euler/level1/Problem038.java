package euler.level1;

import euler.NumberProblem;
import euler.numberic.Number;

public class Problem038 extends NumberProblem {
    @Override
    public Number solve() {
        Number max = Number.ZERO;
        for (int x = 1; x < 10000; x++) {
            Number nr = Number.valueOf(x);
            int y = 2;
            for (; nr.getFilledLength() < 9; y++) {
                nr = nr.concat(Number.valueOf(x * y));
            }
            if (nr.isPandigital9()) {
                if (nr.compareTo(max) > 0) {
                    max = nr;
                }
                // System.out.println(x + "  " + y);
            }
        }
        return max;
    }
}
