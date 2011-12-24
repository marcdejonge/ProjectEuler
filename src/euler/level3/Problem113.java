package euler.level3;

import java.math.BigInteger;

import euler.Problem;

public class Problem113 extends Problem<BigInteger> {
    private BigInteger nCr(int n, int k) {
        // From http://en.wikipedia.org/wiki/Binomial_coefficient#Multiplicative_formula
        BigInteger top = BigInteger.valueOf(n);
        for (int m = 1; m < k; m++) {
            top = top.multiply(BigInteger.valueOf(n - m));
        }
        BigInteger bottom = BigInteger.ONE;
        for (int m = 2; m <= k; m++) {
            bottom = bottom.multiply(BigInteger.valueOf(m));
        }

        return top.divide(bottom);
    }

    @Override
    public BigInteger solve() {
        BigInteger count = BigInteger.ZERO;
        for (int length = 1; length <= 100; length++) {
            count = count.add(nCr(8 + length, length));
            count = count.add(nCr(9 + length, length));
            count = count.subtract(BigInteger.TEN);
        }
        return count;
    }

}
