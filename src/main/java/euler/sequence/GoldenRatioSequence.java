package euler.sequence;

import java.util.function.LongSupplier;
import java.util.function.ToLongBiFunction;
import java.util.stream.LongStream;

public class GoldenRatioSequence implements LongSupplier {
    private final ToLongBiFunction<Long, Long> function;
    private long a, b;

    public GoldenRatioSequence(ToLongBiFunction<Long, Long> function, long a, long b) {
        this.function = function;
        this.a = a;
        this.b = b;
    }

    @Override
    public synchronized long getAsLong() {
        long result = function.applyAsLong(a, b);
        a += b;
        b += a;
        return result;
    }

    public LongStream stream() {
        return LongStream.generate(this);
    }
}
