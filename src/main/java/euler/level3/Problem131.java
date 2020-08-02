package euler.level3;

import euler.IntegerProblem;
import euler.sequence.Primes;

/**
 * m^3 = n^3 + n^2*p
 * 
 * m^3 = (n^2)(n+p) -> n = a^3 & n+p = b^3
 * 
 * p = b^3 - a^3 -> p = (b-a)(a^2 + ab + b^2)
 * 
 * Since p should be prime, (b-a) or (a^2 + ab + b^2) should be 1 and the other p. If the second part is 1, then all the
 * solutions resolve to (b-a) being 1 or -1. This is no solution, so b-a=1 -> b = a + 1 -> p = (a+1)^3 - a^3
 * 
 * p = a^2 + a(a+1) + (a+1)^2 = 3*a^2 + 3*a + 1 < 1000000 -> a <= 576
 * 
 * p = (3 * a)(a + 1) + 1
 */
public class Problem131 extends IntegerProblem {
    @Override
    public long solve() {
        int count = 0;
        for (int a = 1; a <= 576; a++) {
            int p = 3 * a * (a + 1) + 1;
            if (Primes.isPrime(p)) {
                // long n = a * a * a;
                // long m = n * n * n + n * n * p;
                // System.out.printf("Solution %d^3 + %d^2*%d = %d^3%n", n, n, p, m);
                count++;
            }
        }
        return count;
    }
}
