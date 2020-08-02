package euler.level1;

import euler.NumberProblem;
import euler.numberic.Number;

public class Problem048 extends NumberProblem {

    @Override
    public Number solve() {
        Number accum = Number.valueOf(0, 10);
        for (long i = 1; i <= 1000; i++) {
            final Number base = Number.valueOf(i);
            accum = accum.add(base.pow(i, 10));
        }
        return accum;
    }
}
