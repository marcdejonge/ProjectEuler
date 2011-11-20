package euler.level1;

import euler.Problem;

public class Problem014 extends Problem<Integer> {

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
    public Integer solve() {
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
