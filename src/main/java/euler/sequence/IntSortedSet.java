package euler.sequence;

import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.SortedSet;

public class IntSortedSet extends AbstractSet<Integer> implements SortedSet<Integer> {
    private int filled;
    private int[] array;

    public IntSortedSet() {
        this(10);
    }

    public IntSortedSet(int initialSize) {
        if (initialSize <= 0) {
            throw new IllegalArgumentException("initialSize should be larger than zero");
        }
        array = new int[initialSize];
        filled = 0;
    }

    public boolean add(int value) {
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
    public boolean add(Integer value) {
        return add(value.intValue());
    }

    @Override
    public void clear() {
        filled = 0;
        Arrays.fill(array, 0);
    }

    @Override
    public IntSortedSet clone() {
        IntSortedSet newSet = new IntSortedSet(filled == 0 ? 1 : filled);
        System.arraycopy(array, 0, newSet.array, 0, filled);
        newSet.filled = filled;
        return newSet;
    }

    @Override
    public Comparator<? super Integer> comparator() {
        return null;
    }

    private boolean contains(int value) {
        return Arrays.binarySearch(array, 0, filled, value) >= 0;
    }

    @Override
    public boolean contains(Object o) {
        if (o instanceof Number) {
            return contains(((Number) o).intValue());
        } else {
            return false;
        }
    }

    @Override
    public Integer first() {
        if (filled == 0) {
            throw new NoSuchElementException();
        }
        return array[0];
    }

    public long get(int ix) {
        if (ix >= filled) {
            throw new IndexOutOfBoundsException();
        }
        return array[ix];
    }

    @Override
    public IntSortedSet headSet(Integer toElement) {
        return subSet(Integer.MIN_VALUE, toElement);
    }

    @Override
    public boolean isEmpty() {
        return filled == 0;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            private int count = 0;

            @Override
            public boolean hasNext() {
                return count < filled;
            }

            @Override
            public Integer next() {
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

    @Override
    public Integer last() {
        if (filled == 0) {
            throw new NoSuchElementException();
        }
        return array[filled - 1];
    }

    public boolean remove(int value) {
        int position = Arrays.binarySearch(array, 0, filled, value);
        if (position < 0) {
            return false;
        } else {
            for (int ix = filled - 2; ix >= position; ix--) {
                array[ix] = array[ix + 1];
            }
            array[position] = value;
            filled--;
            return true;
        }
    }

    @Override
    public boolean remove(Object o) {
        if (o instanceof Number) {
            return remove(((Number) o).intValue());
        } else {
            return false;
        }
    }

    @Override
    public int size() {
        return filled;
    }

    @Override
    public IntSortedSet subSet(Integer fromElement, Integer toElement) {
        int fromIx = Arrays.binarySearch(array, 0, filled, fromElement);
        int toIx = Arrays.binarySearch(array, 0, filled, toElement);
        if (fromIx < 0) {
            fromIx = -fromIx - 1;
        }
        if (toIx < 0) {
            toIx = -toIx - 1;
        }
        int size = toIx - fromIx;
        if (size < 0) {
            throw new IllegalArgumentException();
        } else if (size == 0) {
            return new IntSortedSet(1);
        }

        IntSortedSet result = new IntSortedSet(size);
        System.arraycopy(array, fromIx, result.array, 0, size);
        result.filled = size;
        return result;
    }

    @Override
    public IntSortedSet tailSet(Integer fromElement) {
        return subSet(fromElement, Integer.MAX_VALUE);
    }
}
