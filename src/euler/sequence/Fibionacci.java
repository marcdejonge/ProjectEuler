package euler.sequence;

public class Fibionacci extends AbstractSequence {
    private long last, current, ix;

    public Fibionacci() {
        last = 1;
        current = 0;
        ix = 0;
    }

    @Override
    public long current() {
        return current;
    }

    @Override
    public long next() {
        final long next = last + current;
        last = current;
        current = next;
        ix++;
        return current;
    }

    @Override
    public long position() {
        return ix;
    }
}
