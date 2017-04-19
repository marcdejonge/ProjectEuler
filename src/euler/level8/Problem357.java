package euler.level8;

import euler.IntegerProblem;
import euler.sequence.Primes;

public class Problem357 extends IntegerProblem {

    public static final long LIMIT = 100000000l;

    @Override
    public long solve() throws Exception {
        return Primes.stream(LIMIT)
                     .parallel()
                     .map(prime -> {
                         final int n = (int) (prime - 1);
                         int d = 2;
                         while (d * d <= n) {
                             final int div = n / d;
                             if (div * d == n && !Primes.isPrime(d + div)) {
                                 return 0;
                             }
                             d++;
                         }
                         return n;
                     })
                     .sum();
    }
}
