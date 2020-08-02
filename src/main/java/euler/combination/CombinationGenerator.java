package euler.combination;

import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CombinationGenerator<T> implements Iterator<T[]>, Iterable<T[]> {

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
    private final int r;
    private final long total;
    private long numLeft;

    public CombinationGenerator(T[] input, int r) {
        int n = input.length;
        if (r > n) {
            throw new IllegalArgumentException("r > input.length");
        }
        if (n < 1) {
            throw new IllegalArgumentException("input is empty");
        }
        this.n = n;
        this.r = r;
        a = new int[r];
        this.input = input;

        BigInteger nFact = getFactorial(n);
        BigInteger rFact = getFactorial(r);
        BigInteger nminusrFact = getFactorial(n - r);
        BigInteger bTotal = nFact.divide(rFact.multiply(nminusrFact));
        if (bTotal.compareTo(BigInteger.valueOf(Long.MAX_VALUE)) > 0) {
            throw new IllegalArgumentException("Too many option to iterate!");
        }
        total = bTotal.longValue();

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
            int i = r - 1;
            while (a[i] == n - r + i) {
                i--;
            }
            a[i] = a[i] + 1;
            for (int j = i + 1; j < r; j++) {
                a[j] = a[i] + j - i;
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
}