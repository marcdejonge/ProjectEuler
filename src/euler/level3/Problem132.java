package euler.level3;

import euler.Problem;
import euler.sequence.Primes;

/**
 * R(k) = (10^k - 1) / 9
 * 
 * p divides R(k) -> (10^k - 1) / 9 % p = 0
 * 
 * p != 3 -> (10^k - 1) % p = 0 -> 10^k % p = 1
 */
public class Problem132 extends Problem<Integer> {
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
    public Integer solve() {
        int sum = 0, count = 0;

        for (long p : new Primes()) {
            if (p > 5 && modpow(10, LIMIT, (int) p) == 1) {
                sum += p;
                count++;
                if (count == 40) {
                    return sum;
                }
            }
        }

        return null;
    }
}
