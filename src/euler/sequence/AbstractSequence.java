package euler.sequence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.function.LongPredicate;
import java.util.function.Supplier;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public abstract class AbstractSequence implements Iterable<Long>, LongSequence, Supplier<AbstractSequence.Pair> {
    public static class Pair {
        private final long position, value;

        public Pair(long position, long value) {
            super();
            this.position = position;
            this.value = value;
        }

        public long getPosition() {
            return position;
        }

        public long getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "(" + position + "->" + value + ")";
        }
    }

    public AbstractSequence() {
    }

    @Override
    public abstract long current();

    @Override
    public LongSequence dropWhile(LongPredicate predicate) {
        long nr = next();
        while (predicate.test(nr)) {
            nr = next();
        }
        return this;
    }

    @Override
    public Pair get() {
        long value = next();
        return new Pair(position(), value);
    }

    public long get(int ix) {
        while (position() < ix) {
            next();
        }
        return current();
    }

    @Override
    public synchronized long getAsLong() {
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
        long[] result = new long[100];

        int ix = 0;
        for (long nr = next(); nr < until; nr = next()) {
            if (ix >= result.length) {
                result = Arrays.copyOf(result, result.length * 2);
            }
            result[ix++] = nr;
        }

        return Arrays.copyOf(result, ix);
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

    public Stream<Pair> pairStream() {
        return Stream.generate(this);
    }

    @Override
    public LongSequence reset() {
        throw new UnsupportedOperationException();
    }

    @Override
    public LongStream stream() {
        return LongStream.generate(this);
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
