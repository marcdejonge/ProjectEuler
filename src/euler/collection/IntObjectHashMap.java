package euler.collection;

import java.util.AbstractCollection;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

public class IntObjectHashMap<T> implements Map<Integer, T> {
    abstract class AbstractIterator<X> implements Iterator<X> {
        private int currentIx = -1;
        private int nextIx = -1;

        @Override
        public final boolean hasNext() {
            if (nextIx > currentIx) {
                return nextIx < buckets.length;
            } else {
                nextIx++;
                while (nextIx < buckets.length) {
                    if (buckets[nextIx] != null) {
                        return true;
                    }
                    nextIx++;
                }
                return false;
            }
        }

        protected final Bucket<T> nextBucket() {
            try {
                if (nextIx == currentIx) {
                    hasNext();
                }
                if (nextIx >= buckets.length) {
                    throw new NoSuchElementException();
                }
                return buckets[nextIx];
            } finally {
                currentIx = nextIx;
            }
        }

        @Override
        public final void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private static class Bucket<T> implements Entry<Integer, T> {
        private final int key;
        private T value;

        Bucket(int key, T value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public Integer getKey() {
            return key;
        }

        public Integer getKeyInt() {
            return key;
        }

        @Override
        public T getValue() {
            return value;
        }

        @Override
        public T setValue(T value) {
            T oldValue = this.value;
            this.value = value;
            return oldValue;
        }
    }

    class EntryIterator extends AbstractIterator<Entry<Integer, T>> {
        @Override
        public Entry<Integer, T> next() {
            return nextBucket();
        }
    }

    class KeyIterator extends AbstractIterator<Integer> {
        @Override
        public Integer next() {
            return nextBucket().getKey();
        }
    }

    class ValueIterator extends AbstractIterator<T> {
        @Override
        public T next() {
            return nextBucket().getValue();
        }
    }

    private static int hash(int val) {
        return val * 0xdeadbeef;
    }

    private int filled;
    private int storeSize;

    private int mask;

    private Bucket<T>[] buckets;

    public IntObjectHashMap() {
        this(4);
    }

    @SuppressWarnings("unchecked")
    public IntObjectHashMap(int size) {
        if (size > 30) {
            size = 30;
        }

        int length = 1 << size;
        buckets = new Bucket[length];
        mask = length - 1;
        filled = 0;
        storeSize = size;
    }

    boolean add(Bucket<T> bucket) {
        checkFilled();

        int ix = findIx(bucket.getKeyInt());
        if (buckets[ix] == null) {
            buckets[ix] = bucket;
            filled++;
            return true;
        } else {
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    private final void checkFilled() {
        if (isFull()) {
            Bucket<T>[] oldBuckets = buckets;
            storeSize++;
            int length = 1 << storeSize;
            buckets = new Bucket[length];
            mask = length - 1;
            filled = 0;
            for (Bucket<T> b : oldBuckets) {
                if (b != null) {
                    add(b);
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        buckets = new Bucket[buckets.length];
        filled = 0;
    }

    public boolean containsKey(int key) {
        int k = ((Number) key).intValue();
        int ix = findIx(k);
        return buckets[ix] != null;
    }

    @Override
    public boolean containsKey(Object key) {
        if (key instanceof Number) {
            return containsKey(((Number) key).intValue());
        } else {
            return false;
        }
    }

    @Override
    public boolean containsValue(Object value) {
        if (value == null) {
            for (Bucket<T> bucket : buckets) {
                if (bucket != null && bucket.getValue() == null) {
                    return true;
                }
            }
        } else {
            for (Bucket<T> bucket : buckets) {
                if (bucket != null && value.equals(bucket.getValue())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Set<Entry<Integer, T>> entrySet() {
        return null;
    }

    private int findIx(int key) {
        checkFilled();

        int ix = hash(key) & mask;
        while (buckets[ix] != null && buckets[ix].getKeyInt() != key) {
            ix = ix + 1 & mask;
        }
        return ix;
    }

    public T get(int key) {
        int ix = findIx(key);
        if (buckets[ix] != null) {
            return buckets[ix].getValue();
        } else {
            return null;
        }
    }

    @Override
    public T get(Object key) {
        if (key instanceof Number) {
            int k = ((Number) key).intValue();
            return get(k);
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        return storeSize == 0;
    }

    private boolean isFull() {
        return buckets.length == filled;
    }

    @Override
    public Set<Integer> keySet() {
        return new AbstractSet<Integer>() {
            @Override
            public Iterator<Integer> iterator() {
                return new KeyIterator();
            }

            @Override
            public int size() {
                return storeSize;
            }
        };
    }

    public T put(int key, T value) {
        if (!add(new Bucket<T>(key, value))) {
            return get(key);
        } else {
            return null;
        }
    }

    @Override
    public T put(Integer key, T value) {
        return put(key.intValue(), value);
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends T> map) {
        for (Entry<? extends Integer, ? extends T> entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public T remove(Object key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        return filled;
    }

    @Override
    public Collection<T> values() {
        return new AbstractCollection<T>() {
            @Override
            public Iterator<T> iterator() {
                return new ValueIterator();
            }

            @Override
            public int size() {
                return storeSize;
            }
        };
    }
}
