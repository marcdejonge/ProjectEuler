package euler.level3;

import euler.Problem;
import euler.numberic.Number;

public class Problem145 extends Problem<Integer> {
    @Override
    public Integer solve() {
        int count = 0;

        for (int i = 1; i < 1000000000; i++) {
            Number x = Number.valueOf(i);
            Number rev = x.reverse();
            if (x.hasZeros(1) || rev.hasZeros(1)) {
                continue;
            }

            Number added = x.addInc(rev);
            if (added.hasOnlyOddDigits()) {
                count++;
            }
        }
        return count;
    }
}
