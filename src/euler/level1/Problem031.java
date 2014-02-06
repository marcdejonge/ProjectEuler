package euler.level1;

import euler.IntegerProblem;
import euler.combination.SumCombinationSolver;

public class Problem031 extends IntegerProblem {
    @Override
    public long solve() {
        return new SumCombinationSolver(new int[] { 1, 2, 5, 10, 20, 50, 100, 200 }, 200).calc(200);
    }
}
