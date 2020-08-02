package euler.sequence;

import java.util.Iterator;

public class IntArrayIterable implements Iterator<Integer>, Iterable<Integer> {
    private final int[] arr;

    private int ix;

    public IntArrayIterable(int[] arr) {
        this.arr = arr;
        ix = 0;
    }

    @Override
    public boolean hasNext() {
        return ix < arr.length;
    }

    @Override
    public Iterator<Integer> iterator() {
        return this;
    }

    @Override
    public Integer next() {
        return arr[ix++];
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
