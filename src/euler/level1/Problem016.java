package euler.level1;

import euler.Problem;
import euler.numberic.Number;

public class Problem016 extends Problem<Integer> {

    @Override
    public Integer solve() {
        final Number nr = Number.valueOf(2).pow(1000);
        return nr.digitalSum();
    }
}
