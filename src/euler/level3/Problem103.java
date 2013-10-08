package euler.level3;

import java.util.Arrays;

import euler.Problem;
import euler.numberic.BitSet;

public final class Problem103 extends Problem<Long> {
    private static BitSet addAndCheck(BitSet originalSums, int number) {
        BitSet newSums = new BitSet(originalSums, originalSums.getLength() + number).shiftRight(number);
        if (originalSums.overlaps(newSums)) {
            return null;
        }
        return newSums.setAll(originalSums);
    }

    private static int sum(int[] array, int fromIx, int untilIx) {
        int sum = 0;
        for (int ix = fromIx; ix <= untilIx; ix++) {
            sum += array[ix];
        }
        return sum;
    }

    private final int count;

    private int currentMin;

    private int[] currentMinimalSolution;

    public Problem103() {
        count = 7;
        currentMin = Integer.MAX_VALUE;
        currentMinimalSolution = null;
    }

    private final void generate(final int[] array, final BitSet sums, final int ix, final int currentSum) {
        if (ix == 0) {
            // When the array is empty, try to start generating the next number
            generate(array, new BitSet(array[0] + 1).set(0).set(array[0]), ix + 1, array[0]);
        } else if (ix == 1) {
            // When the array has only 1 put into it, generate any second number
            for (int x = currentSum + 1; x <= currentSum * 2 && possible(currentSum, x, ix); x++) {
                array[ix] = x;
                generate(array, addAndCheck(sums, x), ix + 1, currentSum + x);
            }
        } else if (ix >= count) {
            // Found a solution, check if it is optimal
            if (currentSum < currentMin) {
                currentMin = currentSum;
                currentMinimalSolution = Arrays.copyOf(array, count);
            }
        } else {
            array[ix] = array[ix - 1];
            while (true) {
                final int x = ++array[ix];

                // Try to detect early if there is no way to create a new minimal solution
                // It calculates the current sum + the current number * nrOfSpotsLeft + triangle nr [nrOfSpotsLeft]
                if (!possible(currentSum, x, ix)) {
                    return;
                }

                // First check for property ii:
                // ii: If B contains more elements than C then S(B) > S(C).
                // When the last number becomes too high, we can return
                for (int sizeOfC = 1; 2 * sizeOfC <= ix; sizeOfC++) {
                    if (sum(array, 0, sizeOfC) <= sum(array, ix - sizeOfC + 1, ix)) {
                        return;
                    }
                }

                // Then generate for property i:
                // i: S(B) ≠ S(C); that is, sums of subsets cannot be equal.
                BitSet newSums = addAndCheck(sums, x);
                if (newSums != null) {
                    generate(array, newSums, ix + 1, currentSum + x);
                }
            }
        }
    }

    private final boolean possible(final int currentSum, final int x, final int ix) {
        int spotsLeft = count - ix;
        return currentSum + x * spotsLeft < currentMin;
    }

    @Override
    public Long solve() {
        int[] array = new int[count];
        array[0] = 1;
        while (array[0] < currentMin / count) {
            generate(array, null, 0, 0);
            array[0]++;
        }

        long result = 0;
        for (int x : currentMinimalSolution) {
            int factor = 10;
            while (x >= factor) {
                factor *= 10;
            }
            result = result * factor;
            result += x;
        }
        return result;
    }
}
