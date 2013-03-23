package euler.sequence;

public class SumSequence extends AbstractSequence {
    public static void main(String[] args) {
        System.out.println(new SumSequence(new NaturalNumbers()));
    }

    private final LongSequence seq;

    private long sum;

    public SumSequence(LongSequence seq) {
        this.seq = seq;
        sum = 0;
    }

    @Override
    public long current() {
        return sum;
    }

    @Override
    public long next() {
        sum += seq.next();
        return sum;
    }

    @Override
    public long position() {
        return seq.position();
    }

    @Override
    public void reset() {
        seq.reset();
        sum = 0;
    }
}
