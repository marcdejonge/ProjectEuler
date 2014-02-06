package euler.level2;

import java.math.BigInteger;

import euler.IntegerProblem;

public class Problem057 extends IntegerProblem {
    @Override
    public long solve() {
        BigInteger top = BigInteger.ONE, bottom = BigInteger.valueOf(2);
        int count = 0;
        BigInteger powerTen = BigInteger.TEN;
        for (int i = 1; i < 1000; i++) {
            top = top.add(bottom.shiftLeft(1));
            final BigInteger temp = top;
            top = bottom;
            bottom = temp;

            final BigInteger added = top.add(bottom);

            while (powerTen.compareTo(added) <= 0) {
                if (powerTen.compareTo(bottom) > 0) {
                    count++;
                }
                powerTen = powerTen.multiply(BigInteger.TEN);
            }
        }

        return count;
    }
}
