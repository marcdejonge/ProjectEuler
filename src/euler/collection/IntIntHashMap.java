package euler.collection;

import java.util.AbstractCollection;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

public class IntIntHashMap implements Map<Integer, Integer> {
    abstract class AbstractIterator<T> implements Iterator<T> {
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

        protected final Bucket nextBucket() {
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

    private static class Bucket implements Entry<Integer, Integer> {
        private final int key;
        private int value;

        Bucket(int key, int value) {
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
        public Integer getValue() {
            return value;
        }

        public int getValueInt() {
            return value;
        }

        @Override
        public Integer setValue(Integer value) {
            int oldValue = this.value;
            this.value = value;
            return oldValue;
        }
    }

    class EntryIterator extends AbstractIterator<Entry<Integer, Integer>> {
        @Override
        public Entry<Integer, Integer> next() {
            return nextBucket();
        }
    }

    class KeyIterator extends AbstractIterator<Integer> {
        @Override
        public Integer next() {
            return nextBucket().getKey();
        }
    }

    class ValueIterator extends AbstractIterator<Integer> {
        @Override
        public Integer next() {
            return nextBucket().getValue();
        }
    }

    private static int hash(int val) {
        return val * 0xdeadbeef;
    }

    private int filled;
    private int storeSize;

    private int mask;

    private Bucket[] buckets;

    public IntIntHashMap() {
        this(4);
    }

    public IntIntHashMap(int size) {
        if (size > 30) {
            size = 30;
        }

        int length = 1 << size;
        buckets = new Bucket[length];
        mask = length - 1;
        filled = 0;
        storeSize = size;
    }

    boolean add(Bucket bucket) {
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

    private final void checkFilled() {
        if (isFull()) {
            Bucket[] oldBuckets = buckets;
            storeSize++;
            int length = 1 << storeSize;
            buckets = new Bucket[length];
            mask = length - 1;
            filled = 0;
            for (Bucket b : oldBuckets) {
                if (b != null) {
                    add(b);
                }
            }
        }
    }

    @Override
    public void clear() {
        buckets = new Bucket[buckets.length];
        filled = 0;
    }

    @Override
    public boolean containsKey(Object key) {
        if (key instanceof Number) {
            int k = ((Number) key).intValue();
            int ix = findIx(k);
            return buckets[ix] != null;
        } else {
            return false;
        }
    }

    @Override
    public boolean containsValue(Object value) {
        if (value instanceof Number) {
            int v = ((Number) value).intValue();
            for (Bucket bucket : buckets) {
                if (bucket != null && bucket.getValueInt() == v) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Set<java.util.Map.Entry<Integer, Integer>> entrySet() {
        return new AbstractSet<Map.Entry<Integer, Integer>>() {
            @Override
            public Iterator<java.util.Map.Entry<Integer, Integer>> iterator() {
                return new EntryIterator();
            }

            @Override
            public int size() {
                return filled;
            }
        };
    }

    private int findIx(int key) {
        int ix = hash(key) & mask;
        while (buckets[ix] != null && buckets[ix].getKeyInt() != key) {
            ix = ix + 1 & mask;
        }
        return ix;
    }

    public int get(int key, int defaultValue) {
        int ix = findIx(key);
        if (buckets[ix] != null) {
            return buckets[ix].getValue();
        } else {
            return defaultValue;
        }
    }

    @Override
    public Integer get(Object key) {
        if (containsKey(key)) {
            return get(((Number) key).intValue(), 0);
        } else {
            return null;
        }
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

    public Integer put(int key, int value) {
        if (!add(new Bucket(key, value))) {
            return get(key);
        } else {
            return null;
        }
    }

    @Override
    public Integer put(Integer key, Integer value) {
        return put(key.intValue(), value.intValue());
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Integer> map) {
        for (Entry<? extends Integer, ? extends Integer> entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public Integer remove(Object key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        return filled;
    }

    @Override
    public Collection<Integer> values() {
        return new AbstractCollection<Integer>() {
            @Override
            public Iterator<Integer> iterator() {
                return new ValueIterator();
            }

            @Override
            public int size() {
                return storeSize;
            }
        };
    }
}
