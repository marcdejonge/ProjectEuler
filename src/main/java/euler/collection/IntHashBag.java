package euler.collection;

import java.util.Arrays;
import java.util.Iterator;

public class IntHashBag implements Iterable<IntHashBag.Element> {
    public final class Element {
        private final int number;
        private volatile int count;

        public Element(int number) {
            this.number = number;
            count = 1;
        }

        public int getCount() {
            return count;
        }

        public int getNumber() {
            return number;
        }

        void increment() {
            count++;
        }

        @Override
        public String toString() {
            return number + " (" + count + "x)";
        }
    }

    private int filled, storeSize;
    private int mask;
    private Element[] buckets;

    public IntHashBag() {
        this(4);
    }

    public IntHashBag(int size) {
        if (size > 30) {
            size = 30;
        }

        int length = 1 << size;
        buckets = new Element[length];
        mask = length - 1;
        filled = 0;
        storeSize = size;
    }

    public boolean add(int nr) {
        checkFilled();

        int ix = findIx(nr);
        if (buckets[ix] == null) {
            buckets[ix] = new Element(nr);
            filled++;
        } else {
            buckets[ix].increment();
        }
        return true;
    }

    public void addAll(IntHashBag store) {
        for (Element nr : store.buckets) {
            add(nr.number);
        }
    }

    private final void checkFilled() {
        if (isFull()) {
            Element[] oldNrs = buckets;
            storeSize++;
            int length = 1 << storeSize;
            buckets = new Element[length];
            mask = length - 1;
            filled = 0;
            for (Element elem : oldNrs) {
                buckets[findIx(elem.getNumber())] = elem;
            }
        }
    }

    public void clear() {
        Arrays.fill(buckets, 0);
        filled = 0;
    }

    public boolean contains(int nr) {
        return buckets[findIx(nr)] != null;
    }

    private int findIx(int nr) {
        int ix = hash(nr);
        while (buckets[ix] != null && buckets[ix].getNumber() != nr) {
            ix = ix + 1 & mask;
        }
        return ix;
    }

    public int getCount(int nr) {
        Element elem = buckets[findIx(nr)];
        return elem == null ? 0 : elem.getCount();
    }

    private int hash(int val) {
        return val * 0xdeadbeef & mask;
    }

    private boolean isFull() {
        return buckets.length == filled;
    }

    @Override
    public Iterator<Element> iterator() {
        return new Iterator<IntHashBag.Element>() {
            private Element current = null;
            private int nextIx = 0;

            private void findNext() {
                while (current == null && nextIx < buckets.length) {
                    current = buckets[nextIx++];
                }
            }

            @Override
            public boolean hasNext() {
                findNext();
                return current != null;
            }

            @Override
            public Element next() {
                findNext();
                try {
                    return current;
                } finally {
                    current = null;
                }
            }
        };
    }

    public int size() {
        return filled;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (Element bucket : buckets) {
            if (bucket != null) {
                sb.append(bucket);
                sb.append(", ");
            }
        }
        if (size() != 0) {
            sb.setLength(sb.length() - 2);
        }
        sb.append("}");
        return sb.toString();
    }
}
