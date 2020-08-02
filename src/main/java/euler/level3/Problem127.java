package euler.level3;

import java.util.Arrays;

import euler.IntegerProblem;

public class Problem127 extends IntegerProblem {
    private static final int LIMIT = 120000;

    /**
     * Calculates the radical numbers for 0 until limit (inclusive). This uses a sieve to generate them all.
     * 
     * @param limit
     *            The number to which the loop should run.
     * @return An array of integers of length (limit + 1) that contains the radicals.
     */
    public static int[] calcRadicals(final int limit) {
        int[] radicals = new int[limit + 1];
        Arrays.fill(radicals, 1);
        for (int nr = 2; nr <= limit; nr++) {
            if (radicals[nr] == 1) {
                for (int x = nr; x <= limit; x += nr) {
                    radicals[x] *= nr;
                }
            }
        }
        return radicals;
    }

    /**
     * Calculate the Greatest Common Divider, using euler's algorithm
     * 
     * Note: this has only been tested with positive number, don't know if it works for negative numbers.
     * 
     * @param a
     *            The first parameter
     * @param b
     *            The second parameter
     * @return The GCD of the 2 values
     */
    public static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }

    @Override
    public long solve() {
        int[] radicals = calcRadicals(LIMIT);

        // Encode all the radical/number pairs in a long
        // The left 32 bits are for the radical, the right 32 bits for the number
        // Sorting these will result in sort by radical, then number
        long[] sortedRadicals = new long[LIMIT + 1];
        for (int ix = 0; ix <= LIMIT; ix++) {
            sortedRadicals[ix] = (long) radicals[ix] << 32 | ix;
        }
        Arrays.sort(sortedRadicals);

        // Now look for combinations, start with each value for c
        long sum = 0;
        for (int cNum = 3; cNum < LIMIT; cNum++) {
            int cRad = radicals[cNum];
            if (cRad * 2 < cNum) { // Check for feasible numbers
                for (long a : sortedRadicals) { // Try each a, by lowest radical first
                    // Decode the radical
                    long aRad = a >>> 32;
                    int aNum = (int) (a & 0xffffffff);
                    int bNum = cNum - aNum;
                    if (aNum < bNum) {
                        long acRad = aRad * cRad;
                        if (acRad * 2 >= cNum) { // If the rad(a*c) becomes too big,...
                            break; // Stop looking for a
                        } else if (acRad * radicals[bNum] < cNum && gcd(aNum, bNum) == 1) { // Do the rest of the checks
                            sum += cNum; // If all succeeds, we have found a (a, b, c) tuple
                        }
                    }
                }
            }
        }

        return sum;
    }
}
