package euler.level2;

import euler.Problem;
import euler.combination.SumCombinationSolver;
import euler.sequence.NaturalNumbers;

public class Problem076 extends Problem<Long> {
    private final static int MAX = 100;

    @Override
    public Long solve() {
        return new SumCombinationSolver(new NaturalNumbers().head(MAX), MAX).calc(MAX);
    }
}
