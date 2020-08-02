package euler.level1;

import euler.IntegerProblem;

public class Problem014 extends IntegerProblem {

    public int chain(long value) {
        int length = 0;
        while (value != 1) {
            if ((value & 1) == 0) {
                value = value >>> 1;
            } else {
                value = value * 3 + 1;
            }
            length++;
        }
        return length;
    }

    @Override
    public long solve() {
        int max = 0, maxNr = 0;
        for (int i = 2; i < 1000000; i++) {
            final int length = chain(i);
            if (length > max) {
                max = length;
                maxNr = i;
            }
        }
        return maxNr;
    }

}
