package euler.level2;

import euler.IntegerProblem;
import euler.SolutionNotFoundException;
import euler.combination.SumCombinationSolver;
import euler.sequence.Primes;

public class Problem077 extends IntegerProblem {
    private static final int MAX = 100;

    @Override
    public long solve() throws SolutionNotFoundException {
        final SumCombinationSolver solver = new SumCombinationSolver(new Primes().head(MAX), MAX);
        for (int x = 2; x < MAX; x++) {
            if (solver.calc(x) > 5000) {
                return x;
            }
        }
        throw new SolutionNotFoundException();
    }
}
