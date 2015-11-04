package euler.level3;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import euler.IntegerProblem;
import euler.input.FileUtils;
import euler.numberic.BitSet;

public final class Problem105 extends IntegerProblem {
    private static int sum(int[] array) {
        int sum = 0;
        for (int x : array) {
            sum += x;
        }
        return sum;
    }

    private static boolean verifyProperty1(final int[] array) {
        BitSet sums = new BitSet(1).set(0);
        for (int nr : array) {
            BitSet newSums = new BitSet(sums, sums.getLength() + nr).shiftRight(nr);
            if (sums.overlaps(newSums)) {
                return false;
            }
            sums = newSums.setAll(sums);
        }
        return true;
    }

    private static boolean verifyProperty2(final int[] array) {
        int sizeOfB = 1, sizeOfC = 0;
        int sumB = array[0], sumC = 0;
        while (sizeOfB + sizeOfC <= array.length) {
            sizeOfB++;
            sizeOfC++;
            sumB += array[sizeOfB - 1];
            sumC += array[array.length - sizeOfC];

            if (sumB <= sumC) {
                return false;
            }
        }
        return true;
    }

    private static boolean verifySpecialSet(int[] array) {
        return verifyProperty2(array) && verifyProperty1(array);
    }

    @Override
    public long solve() throws Exception {
        final List<int[]> sets = new ArrayList<int[]>();
        try (BufferedReader r = FileUtils.readInput(this)) {
            String line = null;
            while ((line = r.readLine()) != null) {
                String[] parts = line.split(",");
                int[] values = new int[parts.length];
                for (int ix = 0; ix < parts.length; ix++) {
                    values[ix] = Integer.valueOf(parts[ix]);
                }
                Arrays.sort(values);
                sets.add(values);
            }
        }

        return sets.stream().parallel().mapToInt(set -> {
            if (verifySpecialSet(set)) {
                return sum(set);
            } else {
                return 0;
            }
        }).sum();
    }
}
