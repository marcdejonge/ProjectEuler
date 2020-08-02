package euler.level3;

import java.math.BigInteger;

import euler.BigIntegerProblem;
import euler.numberic.NumericUtils;
import euler.sequence.Primes;

/**
 * d(p) denotes the number of digits in p
 *
 * Find any solution in the form: x * 10^d(p) + p1 = 0 (mod p2), only x is the unknown here
 *
 * Define t = 10^d(p) mod p2, d = p2 - p1
 *
 * x * t = d (mod p2) -> x * t = d + y * p2 -> x * t - y * p2 = d
 *
 *
 */
public class Problem134 extends BigIntegerProblem {
    @Override
    public BigInteger solve() {
        BigInteger sum = BigInteger.ZERO;

        Primes primes = new Primes();
        primes.head(5); // drop until 5

        long p1 = primes.current(), p2 = primes.next();
        long tenpower = 10;
        while (p1 < 1000000) {
            if (tenpower < p1) {
                tenpower *= 10;
            }

            long inv = NumericUtils.inverseMod(p2 % tenpower, tenpower);
            long x = p1 * inv % tenpower;
            sum = sum.add(BigInteger.valueOf(x * p2));

            p1 = p2;
            p2 = primes.next();
        }

        return sum;
    }
}
