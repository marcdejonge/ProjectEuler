package euler.sequence;

import java.math.BigInteger;

public class BigFibionacci implements Sequence<BigInteger> {
    private BigInteger last, current;
    private final int ix;

    public BigFibionacci() {
        last = current = BigInteger.ONE;
        ix = 0;
    }

    @Override
    public BigInteger next() {
        final BigInteger next = last.add(current);
        last = current;
        current = next;
        return current;
    }

    @Override
    public int position() {
        return ix;
    }
}
