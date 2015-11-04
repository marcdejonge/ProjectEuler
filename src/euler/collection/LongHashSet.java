package euler.collection;

import java.util.Arrays;

public class LongHashSet {
    private int filled, storeSize;
    private int mask;
    private long[] buckets;

    public LongHashSet() {
        this(4);
    }

    public LongHashSet(int size) {
        if (size > 30) {
            size = 30;
        }

        int length = 1 << size;
        buckets = new long[length];
        mask = length - 1;
        filled = 0;
        storeSize = size;
    }

    public boolean add(long nr) {
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

    public void addAll(LongHashSet store) {
        for (long nr : store.buckets) {
            add(nr);
        }
    }

    private final void checkFilled() {
        if (isFull()) {
            long[] oldNrs = buckets;
            storeSize++;
            int length = 1 << storeSize;
            buckets = new long[length];
            mask = length - 1;
            filled = 0;
            for (long n : oldNrs) {
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

    public boolean contains(long nr) {
        return buckets[findIx(nr)] == nr;
    }

    private int findIx(long nr) {
        int ix = hash(nr);
        while (buckets[ix] != 0 && buckets[ix] != nr) {
            ix = ix + 1 & mask;
        }
        return ix;
    }

    private int hash(long val) {
        return ((int) val * 0xdeadbeef ^ (int) (val >> 32) * 0xdeadbeef) & mask;
    }

    private boolean isFull() {
        return buckets.length == filled;
    }

    public int size() {
        return filled;
    }

    public long sum() {
        long sum = 0;
        for (long b : buckets) {
            sum += b;
        }
        return sum;
    }
}
