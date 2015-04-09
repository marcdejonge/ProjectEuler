package euler.level3;

import euler.IntegerProblem;
import euler.sequence.Primes;

public class Problem140 extends IntegerProblem {

    // S = ∑(G_i x^i), G_i=G_(i-1)+G_(i-2), G_1=1, G_2=4
    // S = x + 4x^2 + ∑_(i=3)^∞ (G_i x^i)
    // S = x + 4x^2 + ∑_(i=3)^∞ (x^i G_(i-1) + x^i G_(i-2))
    // S = x + 4x^2 + x ∑_(i=2)^∞ (x^i G_i) + x^2 ∑_(i=1)^∞ (x^i G_i)
    // S = x + 4x^2 + x(S - x) + x^2(S) = 4x^2 + (S + 1)x - x^2 + Sx^2
    // S = (3x^2 + x) / (1 - (x + x^2))
    // (3 + S)x^2 + (S + 1)x - S = 0
    // x = (-(S+1) ± √((S+1)^2 - 4(3+S)(-S))) / (6+2S)
    // x = (√(5S^2 + 14S + 1) - S - 1) / (6 + 2S)
    // x is rational when 5S^2 + 14S + 1 is a perfect square
    @Override
    public long solve() throws Exception {
        long sum = 0, counter = 0;
        for (long y = 2; counter < 30; y++) {
            if (y > Integer.MAX_VALUE) {
                printf("Doesn't fit...%n");
            }
            long x2 = 20 * y * y + 176;
            long x = (long) Math.sqrt(x2);

            if (x * x == x2 && x % 10 == 4) {
                long S = (x - 14) / 10;

                sum += S;
                counter++;

                long a = y - S - 1;
                long b = 6 + 2 * S;

                for (long p : new Primes()) {
                    if (p * p > a) {
                        break;
                    }

                    while (a % p == 0 && b % p == 0) {
                        a /= p;
                        b /= p;
                    }
                }

                printf("x = %d/%d --> S = %d%n", a, b, S);
            }
        }

        return sum;
    }

}
