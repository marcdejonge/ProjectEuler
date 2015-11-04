package euler.level3;

import euler.IntegerProblem;
import euler.numberic.NumericUtils;

public class Problem141 extends IntegerProblem {
    private static final long LIMIT_N = 1_000_000_000_000L;
    private static final int LIMIT_A = (int) Math.pow(LIMIT_N, 1. / 3.);

    /**
     * n = q * d + r
     * q > d > r
     * Geometric series, so choose a value c > 1
     * r, d = c*r, q = c^2*r
     * c should be a rational number, so lets define a/b as the optimal break
     * c = a/b, a > b, gcd(a,b) = 1
     *
     * d = ra/b, q = ra^2/b^2
     * r = xb^2 => d = xab, q = xa^2
     *
     * n = xa^2 * xab + xb^2 = x^2a^3b + xb^2
     */
    @Override
    public long solve() throws Exception {
        long sum = 0;

        for (int a = 2; a < LIMIT_A; a++) {
            long a3 = (long) a * a * a;
            for (int b = 1; b < a && a3 * b + b * b < LIMIT_N; b++) {
                if (NumericUtils.gcd(a, b) == 1) {
                    long a3b = a3 * b;
                    long b2 = (long) b * b;
                    for (long x = 1;; x++) {
                        long n = x * x * a3b + x * b2;
                        if (n >= LIMIT_N) {
                            break;
                        }

                        if (NumericUtils.isSquare(n)) {
                            print("%d = %d * %d + %d %n", n, /*d*/ x * a * b, /*q*/ x * a * a, /*r*/x * b * b);
                            sum += n;
                        }
                    }
                }
            }
        }
        return sum;
    }
}
