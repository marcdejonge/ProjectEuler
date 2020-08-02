package euler.combination;

import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PermutationGenerator<T> implements Iterator<T[]>, Iterable<T[]> {

    private static BigInteger getFactorial(int n) {
        BigInteger fact = BigInteger.ONE;
        for (int i = n; i > 1; i--) {
            fact = fact.multiply(BigInteger.valueOf(i));
        }
        return fact;
    }

    private final T[] input;
    private final int[] a;
    private final int n;
    private final long total;
    private long numLeft;

    public PermutationGenerator(T[] input) {
        this.n = input.length;
        a = new int[input.length];
        this.input = input;

        BigInteger nFact = getFactorial(input.length);
        if (nFact.compareTo(BigInteger.valueOf(Long.MAX_VALUE)) > 0) {
            throw new IllegalArgumentException("Too many option to iterate!");
        }
        total = nFact.longValue();

        reset();
    }

    private T[] createT() {
        @SuppressWarnings("unchecked")
        T[] result = (T[]) Array.newInstance(input.getClass().getComponentType(), a.length);
        int rix = 0;
        for (int ix : a) {
            result[rix++] = input[ix];
        }
        return result;
    }

    public List<T[]> generateAll() {
        if (total > Integer.MAX_VALUE) {
            throw new UnsupportedOperationException("The total number of options in bigger than " + Integer.MAX_VALUE);
        }
        List<T[]> result = new ArrayList<T[]>((int) total);
        for (T[] next : this) {
            result.add(next);
        }
        return result;
    }

    public long getNumLeft() {
        return numLeft;
    }

    public long getTotal() {
        return total;
    }

    @Override
    public boolean hasNext() {
        return numLeft > 0;
    }

    @Override
    public Iterator<T[]> iterator() {
        reset();
        return this;
    }

    @Override
    public T[] next() {
        if (numLeft != total) {
            int k = n - 2;
            while (k >= 0 && a[k] > a[k + 1]) {
                k--;
            }
            if (k < 0) {
                return null;
            }

            int l = n - 1;
            while (a[k] > a[l]) {
                l--;
            }

            swap(k, l);

            int ix1 = k + 1;
            int ix2 = n - 1;
            while (ix1 < ix2) {
                swap(ix1, ix2);
                ix1++;
                ix2--;
            }
        }

        numLeft--;
        return createT();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    public void reset() {
        for (int ix = 0; ix < a.length; ix++) {
            a[ix] = ix;
        }
        numLeft = total;
    }

    private void swap(int k, int l) {
        int temp = a[k];
        a[k] = a[l];
        a[l] = temp;
    }
}