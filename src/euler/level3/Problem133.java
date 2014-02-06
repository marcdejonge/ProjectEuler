package euler.level3;

import static euler.level3.Problem129.A;
import euler.IntegerProblem;
import euler.sequence.Primes;

/**
 * R(k) = (10^k - 1) / 9
 * 
 * R(10^n) = (10^10^n - 1) / 9
 * 
 * R(10^n) % p = 0 -> (10^10^n - 1) / 9 % p = 0
 * 
 * p > 5 -> 10^(10^n) = 1 (mod p)
 * 
 * 10^(p-1) = 1 (mod p) -> 10^n % (p-1) = 0
 */
public class Problem133 extends IntegerProblem {
    private static final int LIMIT = 100000;

    private boolean check(int nr) {
        while (nr % 5 == 0) {
            nr /= 5;
        }
        while ((nr & 1) == 0) {
            nr >>>= 1;
        }
        return nr > 1;
    }

    @Override
    public long solve() {
        long sum = 10; // We skip 2, 3 and 5
        for (int p : new Primes().head(LIMIT)) {
            if (p > 5 && check(A(p))) {
                sum += p;
            }
        }
        return sum;
    }
}
