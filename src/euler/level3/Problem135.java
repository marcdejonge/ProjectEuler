package euler.level3;

import java.util.Arrays;

import euler.IntegerProblem;

public class Problem135 extends IntegerProblem {

    /**
     * x^2 - y^2 - z^2 = n
     *
     * a = y - z, a,z > 0
     *
     * (z+2a)^2 - (z+a)^2 - z^2 = n
     *
     * z^2 + 4az + 4a^2 - z^2 - 2az - a^2 - z^2 = n
     *
     * 3a^2 + 2az - z^2 = n
     *
     * (3a - z) * (a + z) = n
     *
     * Finding the limit (the range that would be out of limit)
     *
     * -z^2 + 2az + 3a^2 - limit = 0
     *
     * z = a ± srqt(4 * a^2 - limit)
     */
    protected int[] generateCounters(final int limit) {
        int[] counters = new int[limit];

        // The limit is reached when z = 1 and thus (3a-1)*(a+1) < LIMIT
        // Thus 4a - 1 < LIMIT
        int maxA = (limit + 1) / 4;
        for (long a = 1; a < maxA; a++) {
            // First determine the range where the result would be bigger than the limit
            double sqrt = Math.sqrt(4 * a * a - limit);
            long lowerBound = (long) Math.ceil(a - sqrt);
            long upperBound = Math.max(1, (long) Math.floor(a + sqrt));

            // Look below the lowerBound
            for (long z = 1; z <= lowerBound; z++) {
                long n = (3 * a - z) * (a + z);
                if (n < limit) {
                    counters[(int) n]++;
                }
            }

            // Look above the upperBound
            for (long z = upperBound; z < 3 * a; z++) {
                long n = (3 * a - z) * (a + z);
                if (n < limit) {
                    counters[(int) n]++;
                }
            }
        }

        return counters;
    }

    @Override
    public long solve() throws Exception {
        return Arrays.stream(generateCounters(1000000)).filter(x -> x == 10).count();
    }
}
