package euler.level2;

import java.util.stream.IntStream;

import euler.IntegerProblem;
import euler.combination.SumCombinationSolver;

public class Problem076 extends IntegerProblem {
    private final static int MAX = 100;

    @Override
    public long solve() {
        return new SumCombinationSolver(IntStream.range(1, MAX).toArray(), MAX).calc(MAX);
    }
}
