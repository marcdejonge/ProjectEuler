package euler.level3;

import euler.Problem;
import euler.combination.BinaryCombinationsWithFixedLength;

public class Problem116 extends Problem<Long> {

    @Override
    public Long solve() {
        return new BinaryCombinationsWithFixedLength(50, 2).getTotalOptions() + new BinaryCombinationsWithFixedLength(50, 3).getTotalOptions()
               + new BinaryCombinationsWithFixedLength(50, 4).getTotalOptions();
    }

}
