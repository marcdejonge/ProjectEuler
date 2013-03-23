package euler.level3;

import euler.Problem;
import euler.numberic.Number;

public class Problem145 extends Problem<Integer> {
    @Override
    public Integer solve() {
        int count = 0;

        for (int i = 12; i < 100000000; i += 2) {
            Number x = Number.valueOf(i);
            if ((x.getDigitAt(0) & 1) == 0) {
                continue;
            }

            Number rev = x.reverse();
            if (x.hasZeros(1) || rev.hasZeros(1)) {
                continue;
            }

            Number added = x.add(rev);
            if (added.hasOnlyOddDigits()) {
                count += 2;
                // System.out.println(x + " + " + rev + " = " + added + " (" + count + ")");
            }
        }
        return count;
    }
}
