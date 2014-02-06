package euler.level3;

import euler.IntegerProblem;
import euler.numberic.NumericUtils;

public class Problem122 extends IntegerProblem {
    private static final int MAX_NR = 200;
    private static final int EXPECTED_MAX_DEPTH = MAX_NR;

    private final int[] minimalDepth;

    public Problem122() {
        minimalDepth = NumericUtils.initArray(MAX_NR + 1, 0, 0, EXPECTED_MAX_DEPTH);
    }

    private void search(final int[] numbers, final int stepsDone) {
        final int lastNr = numbers[stepsDone];
        for (int ix = stepsDone; ix >= 0; ix--) {
            final int nextNumber = lastNr + numbers[ix];
            final int nextStep = stepsDone + 1;
            if (nextNumber <= MAX_NR && nextStep <= minimalDepth[nextNumber]) {
                numbers[nextStep] = nextNumber;
                minimalDepth[nextNumber] = nextStep;
                search(numbers, nextStep);
            }
        }
    }

    @Override
    public long solve() {
        int[] tmpArray = NumericUtils.initArray(EXPECTED_MAX_DEPTH + 1, 1, 0);
        search(tmpArray, 0);
        return NumericUtils.sum(minimalDepth);
    }
}
