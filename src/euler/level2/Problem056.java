package euler.level2;

import euler.Problem;
import euler.numberic.Number;

public class Problem056 extends Problem<Integer> {
    @Override
    public Integer solve() {
        final Number[] nrs = new Number[100];
        for (int i = 0; i < nrs.length; i++) {
            nrs[i] = Number.valueOf(i);
        }

        int max = 0;
        for (final Number nr : nrs) {
            for (int j = 0; j < nrs.length; j++) {
                final int dsum = nr.pow(j).digitalSum();
                if (dsum > max) {
                    max = dsum;
                }
            }
        }
        return max;
    }
}
