package euler.level1;

import euler.IntegerProblem;

public class Problem040 extends IntegerProblem {

    @Override
    public long solve() {
        final byte[] fraction = new byte[1000000];
        int used = 0;
        int i = 1;
        while (used < fraction.length) {
            final char[] number = Integer.toString(i).toCharArray();
            int length = 0;
            if (used + number.length < fraction.length) {
                length = number.length;
            } else {
                length = fraction.length - used;
            }
            for (int j = 0; j < length; j++) {
                fraction[used++] = (byte) (number[j] - '0');
            }
            i++;
        }

        return fraction[0] * fraction[9] * fraction[99] * fraction[999] * fraction[9999] * fraction[99999] * fraction[999999];
    }

}
