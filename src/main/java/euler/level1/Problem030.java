package euler.level1;

import euler.IntegerProblem;
import euler.numberic.NumericUtils;

public class Problem030 extends IntegerProblem {

    @Override
    public long solve() {
        int sum = 0;

        final int[] nrs = new int[] { 0, 0, 0, 0, 0, 2 };
        int value = 2;

        while (value < 1000000) {
            int total = 0;
            for (final int nr : nrs) {
                total += NumericUtils.pow(nr, 5);
            }
            if (total == value) {
                sum += value;
            }

            value++;
            for (int i = nrs.length - 1; i >= 0; i--) {
                nrs[i]++;
                if (nrs[i] >= 10) {
                    nrs[i] = 0;
                } else {
                    break;
                }
            }
        }
        return sum;
    }

}
