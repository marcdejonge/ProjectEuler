package euler.level3;

import static euler.level3.Problem129.A;

import euler.IntegerProblem;
import euler.sequence.Primes;

/**
 * R(k) = (10^k - 1) / 9
 * R(10^n) = (10^10^n - 1) / 9
 * R(10^n) % p = 0 -> (10^10^n - 1) / 9 % p = 0
 * p > 5 -> 10^(10^n) = 1 (mod p)
 * 10^(p-1) = 1 (mod p) -> 10^n % (p-1) = 0
 */
public class Problem133 extends IntegerProblem {
    private static final int LIMIT = 100000;

    private static boolean check(int nr) {
        while (nr % 5 == 0) {
            nr /= 5;
        }
        return (nr - 1 & nr) != 0;
    }

    @Override
    public long solve() {
        return Primes.stream(LIMIT)
                     .parallel()
                     .filter(p -> p > 5 && check(A(p)))
                     .sum()
               + 10; // Since we skip 2, 3 and 5
    }
}
