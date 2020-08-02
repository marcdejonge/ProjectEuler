package euler.sequence;

public class CombinedSequence extends AbstractSequence implements SortedLongSequence {
    private final SortedLongSequence leftSequence, rightSequence;

    private long position;

    public CombinedSequence(SortedLongSequence leftSequence, SortedLongSequence rightSequence) {
        this.leftSequence = leftSequence;
        this.rightSequence = rightSequence;

        reset();
    }

    @Override
    public long current() {
        if (leftSequence.current() <= rightSequence.current()) {
            return leftSequence.current();
        } else {
            return rightSequence.current();
        }
    }

    @Override
    public long next() {
        position++;
        if (leftSequence.current() < rightSequence.current()) {
            return leftSequence.next();
        } else if (leftSequence.current() > rightSequence.current()) {
            return rightSequence.next();
        } else {
            leftSequence.next();
            return rightSequence.next();
        }
    }

    @Override
    public long position() {
        return position;
    }

    @Override
    public CombinedSequence reset() {
        position = 0;

        leftSequence.reset();
        rightSequence.reset();

        return this;
    }
}
