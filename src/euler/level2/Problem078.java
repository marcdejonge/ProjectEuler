package euler.level2;

import euler.IntegerProblem;
import euler.SolutionNotFoundException;

public class Problem078 extends IntegerProblem {
    @Override
    public long solve() throws SolutionNotFoundException {
        final int[] pents = new int[1000];
        for (int ix = 0; ix < pents.length; ix++) {
            final int x = ((ix & 1) == 1 ? -1 : 1) * (ix + 2) / 2;
            pents[ix] = x * (3 * x - 1) / 2;
        }

        final int[] nrs = new int[100000];
        nrs[0] = 1;
        for (int ix = 1; ix < nrs.length; ix++) {
            int total = 0;
            for (int px = 0; px < pents.length; px++) {
                final int p = pents[px];
                if (p > ix) {
                    break;
                }
                if ((px & 2) == 0) {
                    total = (total + nrs[ix - p]) % 1000000;
                } else {
                    total = (total - nrs[ix - p]) % 1000000;
                }
            }
            if (total == 0) {
                return ix;
            }
            nrs[ix] = total;
        }
        throw new SolutionNotFoundException();
    }
}
