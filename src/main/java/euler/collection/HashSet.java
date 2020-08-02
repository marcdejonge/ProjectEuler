package euler.collection;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class HashSet<E extends Hashable> implements Set<E> {
    public static <E extends Hashable> HashSet<E> create(Class<E> clazz, int size) {
        return new HashSet<E>(clazz, size);
    }

    private E[] buckets;
    private int filled, storeSize, maxFilled;
    private int mask;

    public HashSet(Class<E> clazz) {
        this(clazz, 5);
    }

    public HashSet(Class<E> clazz, int size) {
        this(clazz, size, 0.75);
    }

    @SuppressWarnings("unchecked")
    public HashSet(Class<E> clazz, int size, double maxFill) {
        if (size > 30) {
            size = 30;
        }
        if (maxFill > 1) {
            maxFill = 1;
        }

        int length = 1 << size;
        buckets = (E[]) Array.newInstance(clazz, length);
        mask = length - 1;
        filled = 0;
        storeSize = size;
        maxFilled = (int) (length * maxFill);
    }

    @Override
    public boolean add(E element) {
        if (element != null) {
            checkFilled();

            int ix = findIx(element);
            if (buckets[ix] == null) {
                buckets[ix] = element;
                filled++;
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> col) {
        boolean changed = false;
        for (E element : col) {
            changed |= add(element);
        }
        return changed;
    }

    @SuppressWarnings("unchecked")
    private final void checkFilled() {
        if (filled >= maxFilled) {
            E[] oldBuckets = buckets;
            storeSize++;
            int length = 1 << storeSize;
            buckets = (E[]) Array.newInstance(buckets.getClass().getComponentType(), length);
            mask = length - 1;
            filled = 0;
            for (E elem : oldBuckets) {
                if (elem != null) {
                    add(elem);
                }
            }
        }
    }

    @Override
    public void clear() {
        filled = 0;
        Arrays.fill(buckets, null);
    }

    @Override
    public boolean contains(Object obj) {
        if (!(obj instanceof Hashable)) {
            return false;
        } else {
            return buckets[findIx((Hashable) obj)].equals(obj);
        }
    }

    @Override
    public boolean containsAll(Collection<?> col) {
        for (Object obj : col) {
            if (!contains(obj)) {
                return false;
            }
        }
        return true;
    }

    private int findIx(Hashable element) {
        final long hashcode = element.longHashcode();
        int ix = (int)(hashcode & mask);
        int inc = (int) ((hashcode >> 32) | 1);
        while (buckets[ix] != null && !buckets[ix].equals(element)) {
            ix = ix + inc & mask;
        }
        return ix;
    }

    @Override
    public boolean isEmpty() {
        return filled == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int lastIx = -1;
            private int nextIx = -1;

            private void calcNext() {
                if (lastIx == nextIx) {
                    for (nextIx++; nextIx < buckets.length && buckets[nextIx] == null; nextIx++) {
                    }
                }
            }

            @Override
            public boolean hasNext() {
                calcNext();
                return nextIx >= buckets.length;
            }

            @Override
            public E next() {
                calcNext();
                lastIx = nextIx;
                return buckets[nextIx];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override
    public boolean remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> col) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> col) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        return filled;
    }

    @Override
    public Object[] toArray() {
        return toArray(new Object[size()]);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T[] toArray(T[] array) {
        if (array.length < filled) {
            array = (T[]) Array.newInstance(array.getClass().getComponentType(), filled);
        }
        int ix = 0;
        for (E element : buckets) {
            if (element != null) {
                array[ix++] = (T) element;
            }
        }
        return array;
    }

}
