package euler.level3;

import euler.Problem;
import euler.combination.BinaryCombinationsWithFixedLength;

public class Problem117 extends Problem<Long> {

    @Override
    public Long solve() {
        return new BinaryCombinationsWithFixedLength(50, 2, 3, 4).getTotalOptions();
    }

}
