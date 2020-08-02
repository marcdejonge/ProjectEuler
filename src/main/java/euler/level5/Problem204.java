package euler.level5;

import euler.IntegerProblem;

public class Problem204 extends IntegerProblem {

    private final int nrOfHammingNumbers(final int[] primes, final int minIndex, final long base, final long max) {
        if (base > max) {
            return 0;
        }
        int total = 1;
        for (int i = minIndex; i < primes.length; i++) {
            final int x = nrOfHammingNumbers(primes, i, base * primes[i], max);
            if (x == 0) {
                break;
            }
            total += x;
        }
        return total;
    }

    @Override
    public long solve() {
        final int[] primes = new int[] { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97 };

        return nrOfHammingNumbers(primes, 0, 1, 1000000000);
    }

}
