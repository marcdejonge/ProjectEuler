package euler.level1;

import java.util.Arrays;

import euler.IntegerProblem;
import euler.sequence.PentagonalNumbers;

public class Problem044 extends IntegerProblem {
    @Override
    public long solve() {
        final long[] pent = new PentagonalNumbers().head(10000000l);
        for (int x = 1; x < pent.length; x++) {
            for (int y = 1; y <= x; y++) {
                if (Arrays.binarySearch(pent, pent[x] + pent[y]) > 0 && Arrays.binarySearch(pent, pent[x] - pent[y]) > 0) {
                    return pent[x] - pent[y];
                }
            }
        }
        return -1l;
    }

}
