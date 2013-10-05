package euler.level3;

import java.util.Arrays;
import java.util.SortedMap;
import java.util.TreeMap;

import euler.Problem;
import euler.numberic.BitSet;

public class Problem103 extends Problem<Long> {
    private static BitSet addAndCheck(BitSet originalSums, int number) {
        BitSet newSums = new BitSet(originalSums, originalSums.getLength() + number).shiftRight(number);
        if (originalSums.overlaps(newSums)) {
            return null;
        }
        return newSums.setAll(originalSums);
    }

    SortedMap<Integer, int[]> solutions = new TreeMap<>();
    private final int count;
    private int currentMin;
    private final int[] currentSolution;
    private final BitSet[] usedSums;

    public Problem103() {
        count = 7;
        currentMin = Integer.MAX_VALUE;
        currentSolution = new int[count];
        usedSums = new BitSet[count];
    }

    private int endSum(int lastIx, int count) {
        int sum = 0;
        for (int i = 0; i < count; i++) {
            sum += currentSolution[lastIx - i];
        }
        return sum;
    }

    private void generate(int ix, int currentSum) {
        if (ix == 0) {
            int x = ++currentSolution[0];
            currentSolution[1] = x;
            usedSums[0] = new BitSet(x + 1).set(0).set(x);
            generate(1, x);
        } else if (ix == 1) {
            for (int x = currentSum + 1; x <= Math.min(currentSum * 2, currentMin - (count - 2) * x); x++) {
                currentSolution[1] = x;
                currentSolution[2] = x;
                usedSums[1] = addAndCheck(usedSums[0], x);
                generate(2, currentSum + x);
            }
        } else if (ix >= count) {
            // Found one!
            if (!solutions.containsKey(currentSum)) {
                solutions.put(currentSum, Arrays.copyOf(currentSolution, count));
                if (currentSum < currentMin) {
                    currentMin = currentSum;
                }
            }
        } else {
            while (true) {
                int x = ++currentSolution[ix];
                if (ix + 1 < count) {
                    currentSolution[ix + 1] = x;
                }

                if (currentSum + x * (count - ix - 1) > currentMin) {
                    return;
                }

                // First check for property ii:
                // ii: If B contains more elements than C then S(B) > S(C).
                for (int count = 1; 2 * count <= ix; count++) {
                    if (startSum(count + 1) <= endSum(ix, count)) {
                        return;
                    }
                }

                // Then generate for property i:
                // i: S(B) ≠ S(C); that is, sums of subsets cannot be equal.
                usedSums[ix] = addAndCheck(usedSums[ix - 1], x);
                if (usedSums[ix] != null) {
                    generate(ix + 1, currentSum + x);
                }
            }
        }
    }

    @Override
    public Long solve() {
        while (currentSolution[0] < currentMin / count) {
            generate(0, 0);
        }

        long result = 0;
        int[] solution = solutions.get(currentMin);
        for (int x : solution) {
            result = result * 100;
            result += x;
        }
        return result;
    }

    private int startSum(int count) {
        int sum = 0;
        for (int ix = 0; ix < count; ix++) {
            sum += currentSolution[ix];
        }
        return sum;
    }
}
