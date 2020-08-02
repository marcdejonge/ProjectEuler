package euler.sequence;

public abstract class SimpleSequence extends AbstractSequence {
    private long n;

    public long getN() {
        return n;
    }

    @Override
    public long next() {
        n++;
        return current();
    }

    @Override
    public long position() {
        return n;
    }

    @Override
    public SimpleSequence reset() {
        n = 0;
        return this;
    }

    protected void setN(long start) {
        n = start;
    }
}
