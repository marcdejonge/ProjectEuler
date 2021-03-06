package euler.level1;

import java.math.BigInteger;

import euler.IntegerProblem;

public class Problem025 extends IntegerProblem {
    @Override
    public long solve() {
        BigInteger f1 = BigInteger.ONE, f2 = BigInteger.ONE;
        BigInteger res = f1.add(f2);
        final BigInteger min = BigInteger.TEN.pow(999);
        int term = 3;
        while (res.compareTo(min) < 0) {
            f1 = f2;
            f2 = res;
            res = f1.add(f2);
            term++;
        }
        return term;
    }
}
