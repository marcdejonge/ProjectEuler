package euler.level2;

import euler.IntegerProblem;
import euler.SolutionNotFoundException;

public class Problem071 extends IntegerProblem {
    @Override
    public long solve() throws SolutionNotFoundException {
        for (int num = 1000000; num >= 2; num--) {
            final int den = num * 3 / 7;
            if (num * 3 - den * 7 == 1) {
                return den;
            }
        }
        throw new SolutionNotFoundException();
    }
}
