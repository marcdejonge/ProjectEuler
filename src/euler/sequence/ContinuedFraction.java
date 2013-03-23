package euler.sequence;

import euler.Pair;
import euler.numberic.Number;

public class ContinuedFraction {
    private final LongSequence seq;

    private int length;

    public ContinuedFraction(LongSequence sd) {
        seq = sd;
        length = 1;
    }

    public Pair<Number, Number> get(int ix) {
        length += ix - 1;
        return next();
    }

    public Pair<Number, Number> next() {
        Number num = Number.ZERO;
        Number den = Number.ONE;
        final long[] xs = new long[length];
        seq.reset();
        for (int ix = 0; ix < length; ix++) {
            xs[ix] = seq.next();
        }

        for (int ix = length - 1; ix >= 0; ix--) {
            final Number num2 = den;
            final Number den2 = den.multiply(Number.valueOf(xs[ix])).add(num);

            num = num2;
            den = den2;
        }

        length++;
        return Pair.from(den, num);
    }
}
