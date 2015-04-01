package euler.sequence;

public class IncreasingNumbers extends AbstractSequence implements SortedLongSequence {
    private final LongSortedSet numbers;
    private int factor;
    private int position;

    public IncreasingNumbers() {
        numbers = new LongSortedSet(1000);
        for (int i = 0; i < 10; i++) {
            numbers.add(i);
        }
        factor = 1;
    }

    @Override
    public long current() {
        return numbers.get(position);
    }

    @Override
    public long next() {
        if (position >= numbers.size() - 1) {
            int start = position;
            for (int n = 1; n < 10; n++) {
                for (int ix = start; ix >= 0 && numbers.get(ix) >= factor; ix--) {
                    long nr = numbers.get(ix);
                    if (nr / factor >= n) {
                        numbers.add(n * 10 * factor + nr);
                    }
                }
            }
            factor *= 10;
        }
        return numbers.get(position++);
    }

    @Override
    public long position() {
        return position;
    }

    @Override
    public IncreasingNumbers reset() {
        position = 0;
        return this;
    }
}
