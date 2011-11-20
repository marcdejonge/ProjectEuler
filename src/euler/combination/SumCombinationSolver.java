package euler.combination;

public class SumCombinationSolver {
    private final int[] options;

    private final long[][] solutions;

    public SumCombinationSolver(int[] options, int until) {
        this.options = options.clone();
        solutions = new long[until + 1][options.length + 1];
    }

    public long calc(int x) {
        int ix = 0;
        while (ix < options.length && options[ix] < x) {
            ix++;
        }
        return calc(x, ix);
    }

    private final long calc(final int x, final int limit) {
        if (x == 0) {
            return 1;
        } else if (limit == 0) {
            if (x % options[0] == 0) {
                return 1;
            } else {
                return 0;
            }
        } else if (solutions[x][limit] == 0) {
            long total = 0;
            for (int ix = 0; ix <= limit && ix < options.length; ix++) {
                if (x - options[ix] >= 0) {
                    total += calc(x - options[ix], ix);
                } else {
                    break;
                }
            }
            return solutions[x][limit] = total;
        } else {
            return solutions[x][limit];
        }
    }
}
