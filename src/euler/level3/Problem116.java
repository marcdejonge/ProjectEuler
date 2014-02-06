package euler.level3;

import euler.IntegerProblem;
import euler.combination.BinaryCombinationsWithFixedLength;

public class Problem116 extends IntegerProblem {

    @Override
    public long solve() {
        return new BinaryCombinationsWithFixedLength(50, 2).getTotalOptions() + new BinaryCombinationsWithFixedLength(50, 3).getTotalOptions()
               + new BinaryCombinationsWithFixedLength(50, 4).getTotalOptions()
               - 3; // Ignore all the empty bars
    }

}
