package euler.sequence;

public class InverseSequence extends AbstractSequence implements SortedLongSequence {
    private final SortedLongSequence otherSequence;
    private long position;
    private long current;

    public InverseSequence(SortedLongSequence otherSequence) {
        this.otherSequence = otherSequence;
        reset();
    }

    @Override
    public long current() {
        return current;
    }

    @Override
    public long next() {
        long result = current;
        position++;

        current++;
        while (current == otherSequence.current()) {
            otherSequence.next();
            current++;
        }

        return result;
    }

    @Override
    public long position() {
        return position;
    }

    @Override
    public InverseSequence reset() {
        otherSequence.reset();
        position = 0;
        current = 0;
        while (current == otherSequence.current()) {
            current++;
            otherSequence.next();
        }
        return this;
    }
}
