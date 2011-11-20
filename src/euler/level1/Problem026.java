package euler.level1;

import euler.Problem;
import euler.numberic.NumericUtils;

public class Problem026 extends Problem<Integer> {
    @Override
    public Integer solve() {
        // System.out.println(Arrays.toString(NumericUtils.recurringCycle(7)));
        int max = 0;
        int maxNr = 0;
        for (int i = 1; i < 1000; i++) {
            final int[] d = NumericUtils.recurringCycle(i);
            if (d.length > max) {
                max = d.length;
                maxNr = i;
            }
        }
        return maxNr;
    }
}
