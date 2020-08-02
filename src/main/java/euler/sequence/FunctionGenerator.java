package euler.sequence;

public class FunctionGenerator extends AbstractSequence {
    private long index, value, increment;
    private final long increment2;

    public FunctionGenerator(long a, long b, long c) {
        index = 0;
        value = c;
        increment = a + b;
        increment2 = 2 * a;
    }

    @Override
    public long current() {
        return value;
    }

    @Override
    public long next() {
        value += increment;
        increment += increment2;
        index++;
        return value;
    }

    @Override
    public long position() {
        return index;
    }
}
