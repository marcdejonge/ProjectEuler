package euler.sequence;

import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Iterator;

public class LongSortedSet extends AbstractSet<Long> {
    private int filled;
    private long[] array;

    public LongSortedSet() {
        this(10);
    }

    public LongSortedSet(int initialSize) {
        if (initialSize <= 0) {
            throw new IllegalArgumentException("initialSize should be larger than zero");
        }
        array = new long[initialSize];
        filled = 0;
    }

    public boolean add(long value) {
        int insert = Arrays.binarySearch(array, 0, filled, value);
        if (insert >= 0) {
            return false;
        } else {
            insert = -insert - 1;
            if (filled == array.length) {
                array = Arrays.copyOf(array, array.length * 2 + 1);
            }
            for (int ix = filled; ix > insert; ix--) {
                array[ix] = array[ix - 1];
            }
            array[insert] = value;
            filled++;
            return true;
        }
    }

    @Override
    public boolean add(Long value) {
        return add(value.longValue());
    }

    @Override
    public void clear() {
        filled = 0;
        Arrays.fill(array, 0);
    }

    private boolean contains(long value) {
        return Arrays.binarySearch(array, 0, filled, value) >= 0;
    }

    @Override
    public boolean contains(Object o) {
        if (o instanceof Long) {
            return contains(((Long) o).longValue());
        } else {
            return false;
        }
    }

    public long get(int ix) {
        if (ix >= filled) {
            throw new IndexOutOfBoundsException();
        }
        return array[ix];
    }

    @Override
    public boolean isEmpty() {
        return filled == 0;
    }

    @Override
    public Iterator<Long> iterator() {
        return new Iterator<Long>() {
            private int count = 0;

            @Override
            public boolean hasNext() {
                return count < filled;
            }

            @Override
            public Long next() {
                if (!hasNext()) {
                    throw new IllegalStateException();
                }
                return array[count++];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    public boolean remove(long value) {
        int position = Arrays.binarySearch(array, 0, filled, value);
        if (position < 0) {
            return false;
        } else {
            for (int ix = filled - 2; ix >= position; ix--) {
                array[ix] = array[ix + 1];
            }
            array[position] = value;
            filled++;
            return true;
        }
    }

    @Override
    public boolean remove(Object o) {
        if (o instanceof Long) {
            return remove(((Long) o).longValue());
        } else {
            return false;
        }
    }

    @Override
    public int size() {
        return filled;
    }
}
