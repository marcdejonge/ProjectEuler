package euler.collection;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class LongHashBag implements Iterable<LongHashBag.Element> {
    public final class Element {
        private final long number;
        private volatile int count;

        public Element(long number) {
            this.number = number;
            count = 1;
        }

        public int getCount() {
            return count;
        }

        public long getNumber() {
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

    public LongHashBag() {
        this(4);
    }

    public LongHashBag(int size) {
        if (size > 30) {
            size = 30;
        }

        int length = 1 << size;
        buckets = new Element[length];
        mask = length - 1;
        filled = 0;
        storeSize = size;
    }

    public boolean add(long nr) {
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

    public void addAll(LongHashBag store) {
        for (Element nr : store.buckets) {
            add(nr.number);
        }
    }

    private final void checkFilled() {
        if (buckets.length * 0.75 < filled) {
            Element[] oldNrs = buckets;
            storeSize++;
            int length = 1 << storeSize;
            buckets = new Element[length];
            mask = length - 1;
            for (Element elem : oldNrs) {
                if (elem != null) {
                    buckets[findIx(elem.getNumber())] = elem;
                }
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

    private int findIx(long nr) {
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

    private int hash(long val) {
        return (int) (val * 0xdeadbeef) & mask;
    }

    @Override
    public Iterator<Element> iterator() {
        return new Iterator<LongHashBag.Element>() {
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

    public Stream<Element> stream() {
        return StreamSupport.stream(new Spliterators.AbstractSpliterator<Element>(size(), 0) {
            private int nextIx = 0;

            @Override
            public boolean tryAdvance(Consumer<? super Element> action) {
                while (nextIx < buckets.length) {
                    Element elem = buckets[nextIx++];
                    if (elem != null) {
                        action.accept(elem);
                        return true;
                    }
                }
                return false;
            }
        }, false);
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
