package euler.sequence;

import java.util.function.ToLongBiFunction;

public class GoldenRatioSequence extends AbstractSequence {
    private final ToLongBiFunction<Long, Long> function;
    private int position;
    private long result;
    private long a, b;

    public GoldenRatioSequence(ToLongBiFunction<Long, Long> function, long a, long b) {
        this.function = function;
        this.a = a;
        this.b = b;
        result = 0;
        position = 0;
    }

    @Override
    public long current() {
        return result;
    }

    @Override
    public long next() {
        result = function.applyAsLong(a, b);
        a += b;
        b += a;
        position++;
        return result;
    }

    @Override
    public long position() {
        return position;
    }
}
