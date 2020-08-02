package euler.level3;

import euler.IntegerProblem;
import euler.SolutionNotFoundException;
import euler.sequence.Primes;

/**
 * R(k) = (10^k - 1) / 9
 * 
 * p divides R(k) -> (10^k - 1) / 9 = 0 (mod p)
 * 
 * p != 3 -> (10^k - 1) = 0 (mod 9p) -> 10^k = 1 (mod 9p)
 */
public class Problem132 extends IntegerProblem {
    private static final int LIMIT = 1000000000;

    public long modpow(int base, int exp, int mod) {
        long result = 1, power = base;
        for (int i = 1; i <= exp;) {
            if ((exp & i) != 0) {
                result = result * power % mod;
            }
            i <<= 1;
            power = power * power % mod;
        }
        return result;
    }

    @Override
    public long solve() throws SolutionNotFoundException {
        int sum = 0, count = 0;

        for (int p : new Primes().head(200000)) {
            if (p > 5 && modpow(10, LIMIT, 9 * p) == 1) {
                sum += p;
                count++;
                if (count == 40) {
                    return sum;
                }
            }
        }

        throw new SolutionNotFoundException();
    }
}
