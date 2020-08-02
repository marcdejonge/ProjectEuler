package euler.level1;

import euler.IntegerProblem;
import euler.sequence.LineSequence;

public class Problem043 extends IntegerProblem {

    @Override
    public long solve() {
        final LineSequence seq = new LineSequence(new byte[] { 0, 9, 8, 7, 6, 5, 4, 3, 2, 1 });

        long sum = 0;
        while (seq.next()) {
            final byte[] pan = seq.current();

            final int d2 = pan[1] * 100 + pan[2] * 10 + pan[3];
            final int d3 = pan[2] * 100 + pan[3] * 10 + pan[4];
            final int d4 = pan[3] * 100 + pan[4] * 10 + pan[5];
            final int d5 = pan[4] * 100 + pan[5] * 10 + pan[6];
            final int d6 = pan[5] * 100 + pan[6] * 10 + pan[7];
            final int d7 = pan[6] * 100 + pan[7] * 10 + pan[8];
            final int d8 = pan[7] * 100 + pan[8] * 10 + pan[9];

            if (d2 % 2 == 0 && d3 % 3 == 0 && d4 % 5 == 0 && d5 % 7 == 0 && d6 % 11 == 0 && d7 % 13 == 0 && d8 % 17 == 0) {
                final long value = seq.toNumber().longValue();
                sum += value;
            }
        }

        return sum;
    }

}
