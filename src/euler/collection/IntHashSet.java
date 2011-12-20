package euler.collection;

import java.util.Arrays;

public class IntHashSet {
    private int filled, storeSize;
    private int mask;
    private int[] buckets;

    public IntHashSet() {
        this(4);
    }

    public IntHashSet(int size) {
        if (size > 30) {
            size = 30;
        }

        int length = 1 << size;
        buckets = new int[length];
        mask = length - 1;
        filled = 0;
        storeSize = size;
    }

    public boolean add(int nr) {
        checkFilled();

        int ix = findIx(nr);
        if (buckets[ix] == 0) {
            buckets[ix] = nr;
            filled++;
            return true;
        } else {
            return false;
        }
    }

    public void addAll(IntHashSet store) {
        for (int nr : store.buckets) {
            add(nr);
        }
    }

    private final void checkFilled() {
        if (isFull()) {
            int[] oldNrs = buckets;
            storeSize++;
            int length = 1 << storeSize;
            buckets = new int[length];
            mask = length - 1;
            filled = 0;
            for (int n : oldNrs) {
                if (n != 0) {
                    add(n);
                }
            }
        }
    }

    public void clear() {
        Arrays.fill(buckets, 0);
        filled = 0;
    }

    public boolean contains(int nr) {
        return buckets[findIx(nr)] == nr;
    }

    private int findIx(int nr) {
        int ix = hash(nr);
        while (buckets[ix] != 0 && buckets[ix] != nr) {
            ix = ix + 1 & mask;
        }
        return ix;
    }

    private int hash(int val) {
        return val * 0xdeadbeef & mask;
    }

    private boolean isFull() {
        return buckets.length == filled;
    }
}
