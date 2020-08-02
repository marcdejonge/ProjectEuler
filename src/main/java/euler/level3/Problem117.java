package euler.level3;

import euler.IntegerProblem;
import euler.combination.BinaryCombinationsWithFixedLength;

public class Problem117 extends IntegerProblem {

    @Override
    public long solve() {
        return new BinaryCombinationsWithFixedLength(50, 2, 3, 4).getTotalOptions();
    }

}
