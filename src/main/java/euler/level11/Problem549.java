package euler.level11;

import euler.IntegerProblem;

public class Problem549 extends IntegerProblem {
    private final static int MAX = 100_000_000;

    @Override
    public long solve() {
        int[] solutions = new int[MAX + 1];
        for (int nr = 2; nr < solutions.length; nr++) {
            if (solutions[nr] == 0) {
                // The number is a prime!
                long step = nr;
                int sol = nr;
                int factor = 1;

                while (true) {
                    if (step > MAX) {
                        break;
                    }

                    for (int multiple = (int) step; multiple <= MAX; multiple += step) {
                        if (solutions[multiple] < sol) {
                            solutions[multiple] = sol;
                        }
                    }

                    int f = factor;
                    factor++;
                    step *= nr;
                    sol += nr;
                    while (f % nr == 0) {
                        step *= nr;
                        f /= nr;
                    }
                }
            }
        }

        long sum = 0;
        for (int solution : solutions) {
            sum += solution;
        }
        return sum;
    }
}
