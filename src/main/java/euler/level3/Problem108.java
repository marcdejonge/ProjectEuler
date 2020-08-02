package euler.level3;

import java.math.BigInteger;

import euler.IntegerProblem;
import euler.sequence.Primes;

public class Problem108 extends IntegerProblem {
    private final BigInteger[] primes;

    private final int max;

    public Problem108() {
        this(1000);
    }

    public Problem108(int max) {
        primes = new BigInteger[1000];
        Primes ps = new Primes();
        for (int ix = 0; ix < primes.length; ix++) {
            primes[ix] = BigInteger.valueOf(ps.next());
        }
        this.max = max;
    }

    private BigInteger minimalNumber(long minDivsOfn2, BigInteger n, long divsOfn2, BigInteger currentMin, int maxTimes, int depth) {
        if (n.compareTo(currentMin) > 0) {
            return currentMin;
        } else if (divsOfn2 >= minDivsOfn2) {
            return n;
        } else {
            BigInteger min = currentMin;
            BigInteger prime = primes[depth];
            for (int times = 2; times <= maxTimes && n.compareTo(min) < 0; times += 2) {
                n = n.multiply(prime);
                BigInteger next = minimalNumber(minDivsOfn2,
                                                n,
                                                divsOfn2 * (times + 1),
                                                currentMin.compareTo(min) < 0 ? currentMin : min,
                                                times,
                                                depth + 1);
                if (next.compareTo(min) <= 0) {
                    min = next;
                } else {
                    break;
                }
            }
            return min;
        }
    }

    private long nrOfSolutions(int minDivs) {
        return minimalNumber(minDivs * 2, BigInteger.ONE, 1, BigInteger.valueOf(Long.MAX_VALUE), Integer.MAX_VALUE, 0).longValue();
    }

    @Override
    public long solve() {
        return nrOfSolutions(max);
    }

}
