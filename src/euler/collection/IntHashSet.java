package euler.collection;

import java.util.Arrays;

public class IntHashSet {
    private static class IntHashSetStore {
        private int filled;
        private final int mask;
        private final int[] buckets;

        IntHashSetStore(int size) {
            if (size > 30) {
                size = 30;
            }

            int length = 1 << size;
            buckets = new int[length];
            mask = length - 1;
            filled = 0;
        }

        boolean add(int nr) {
            int ix = findIx(nr);
            if (buckets[ix] == 0) {
                buckets[ix] = nr;
                filled++;
                return true;
            } else {
                return false;
            }
        }

        void addAll(IntHashSetStore store) {
            for (int nr : store.buckets) {
                add(nr);
            }
        }

        void clear() {
            Arrays.fill(buckets, 0);
            filled = 0;
        }

        boolean contains(int nr) {
            return buckets[findIx(nr)] == nr;
        }

        int findIx(int nr) {
            int ix = hash(nr);
            while (buckets[ix] != 0 && buckets[ix] != nr) {
                ix = ix + 1 & mask;
            }
            return ix;
        }

        int hash(int val) {
            return val * 0xdeadbeef & mask;
        }

        boolean isFull() {
            return buckets.length == filled;
        }
    }

    private int storeSize;

    private IntHashSetStore store;

    public IntHashSet() {
        this(4);
    }

    public IntHashSet(int size) {
        store = new IntHashSetStore(size);
        storeSize = size;
    }

    public boolean add(int nr) {
        if (store.isFull()) {
            IntHashSetStore oldStore = store;
            storeSize++;
            store = new IntHashSetStore(storeSize);
            store.addAll(oldStore);
        }
        return store.add(nr);
    }

    public void clear() {
        store.clear();
    }

    public boolean contains(int nr) {
        return store.contains(nr);
    }
}
