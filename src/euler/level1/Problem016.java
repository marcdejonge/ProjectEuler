package euler.level1;

import euler.IntegerProblem;
import euler.numberic.Number;

public class Problem016 extends IntegerProblem {

    @Override
    public long solve() {
        final Number nr = Number.valueOf(2).pow(1000);
        return nr.digitalSum();
    }
}
