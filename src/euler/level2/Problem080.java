package euler.level2;

import java.math.BigInteger;

import euler.Problem;

public class Problem080 extends Problem<Integer> {

    private static final BigInteger HUNDRED = BigInteger.valueOf(100);

    @Override
    public Integer solve() {
        int sum = 0;
        for (int x = 2; x < 100; x++) {
            int sqrt = (int) Math.sqrt(x);
            if (sqrt * sqrt != x) {
                sum += sqrtDigits(BigInteger.valueOf(x), BigInteger.valueOf(sqrt), 99) + sqrt;
            }
        }
        return sum;
    }

    public int sqrtDigits(BigInteger nr, BigInteger sqr, int limit) {
        if (limit == 0) {
            return 0;
        } else {
            BigInteger newNr = nr.multiply(HUNDRED);
            BigInteger newSqr = sqr.multiply(BigInteger.TEN);
            int x = 0;
            while (x < 10) {
                ;
                BigInteger testSqr = newSqr.add(BigInteger.ONE);
                if (testSqr.multiply(testSqr).compareTo(newNr) > 0) {
                    break;
                }
                newSqr = testSqr;
                x++;
            }
            return sqrtDigits(newNr, newSqr, limit - 1) + x;
        }
    }
}
