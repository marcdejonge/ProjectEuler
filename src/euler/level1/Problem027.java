package euler.level1;

import euler.IntegerProblem;
import euler.sequence.Primes;

public class Problem027 extends IntegerProblem {

    @Override
    public long solve() {
        int max = 0;
        int maxA = 0, maxB = 0;
        for (int a = -999; a < 1000; a++) {
            for (int b = -999; b < 1000; b++) {
                int nr = 0;
                while (Primes.isPrime(nr * nr + a * nr + b)) {
                    nr++;
                }
                if (nr > max) {
                    max = nr;
                    maxA = a;
                    maxB = b;
                }
            }
        }

        return maxA * maxB;
    }
}
