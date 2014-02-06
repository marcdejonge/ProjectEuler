package euler.level2;

import euler.IntegerProblem;
import euler.combination.SumCombinationSolver;
import euler.sequence.NaturalNumbers;

public class Problem076 extends IntegerProblem {
    private final static int MAX = 100;

    @Override
    public long solve() {
        return new SumCombinationSolver(new NaturalNumbers().head(MAX), MAX).calc(MAX);
    }
}
