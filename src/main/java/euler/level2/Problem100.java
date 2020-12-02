package euler.level2;

import euler.IntegerProblem;
import euler.JavaPair;
import euler.combination.PellSolver;

public class Problem100 extends IntegerProblem {

    private static final long MAX = 1000000000000L;

    @Override
    public long solve() {
        PellSolver ps = new PellSolver(8);

        JavaPair<Long, Long> pair = null;
        long total = 0, nrBlueBalls = 0;
        while (total < MAX) {
            pair = ps.next();
            long nrRedBalls = pair.getSecond();
            nrBlueBalls = (pair.getFirst() + 1) / 2 + nrRedBalls;
            total = nrBlueBalls + nrRedBalls;
        }
        return nrBlueBalls;
    }

}
