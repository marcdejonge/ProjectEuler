package euler.sequence;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class AbstractSequence implements Iterable<Long>, LongSequence {
    public static abstract interface Test {
        public boolean test(long value);
    }

    public AbstractSequence() {
    }

    /* (non-Javadoc)
     * @see euler.sequence.LongSequence#current()
     */
    @Override
    public abstract long current();

    /* (non-Javadoc)
     * @see euler.sequence.LongSequence#dropWhile(euler.sequence.AbstractSequence.Test)
     */
    @Override
    public void dropWhile(Test test) {
        reset();
        long nr = next();
        while (test.test(nr)) {
            nr = next();
        }
    }

    public long get(int ix) {
        for (int i = 1; i < ix; i++) {
            next();
        }
        return next();
    }

    public int[] head(int until) {
        final ArrayList<Integer> list = new ArrayList<Integer>();
        for (long nr = next(); nr < until; nr = next()) {
            list.add((int) nr);
        }

        final int[] result = new int[list.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = list.get(i).intValue();
        }

        return result;
    }

    /* (non-Javadoc)
     * @see euler.sequence.LongSequence#head(long)
     */
    @Override
    public long[] head(long until) {
        final ArrayList<Long> list = new ArrayList<Long>();
        for (long nr = next(); nr < until; nr = next()) {
            list.add(nr);
        }

        final long[] result = new long[list.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = list.get(i).longValue();
        }

        return result;
    }

    @Override
    public Iterator<Long> iterator() {
        return new Iterator<Long>() {
            @Override
            public boolean hasNext() {
                return true; // There is always a next
            }

            @Override
            public Long next() {
                return AbstractSequence.this.next();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    /* (non-Javadoc)
     * @see euler.sequence.LongSequence#next()
     */
    @Override
    public abstract long next();

    /* (non-Javadoc)
     * @see euler.sequence.LongSequence#position()
     */
    @Override
    public abstract long position();

    /* (non-Javadoc)
     * @see euler.sequence.LongSequence#reset()
     */
    @Override
    public void reset() {
        throw new UnsupportedOperationException();
    }

    /**
     * This returns the first 100 numbers and ends with three dots. Remember that this tries to call {@link #next()} 100
     * times from the current point.
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + " [current = " + current() + "]";
    }

    public String toString(int max) {
        final StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < max; i++) {
            sb.append(next());
            sb.append(", ");
        }
        sb.append("...");
        return sb.toString();
    }
}
