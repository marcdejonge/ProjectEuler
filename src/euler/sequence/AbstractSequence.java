package euler.sequence;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class AbstractSequence implements Iterable<Long>, LongSequence {
    public static abstract interface Test {
        public boolean test(long value);
    }

    public AbstractSequence() {
    }

    @Override
    public abstract long current();

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

    @Override
    public abstract long next();

    @Override
    public abstract long position();

    @Override
    public void reset() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " [current = " + current() + "]";
    }

    @Override
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
