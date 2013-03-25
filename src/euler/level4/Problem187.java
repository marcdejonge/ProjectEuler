package euler.level4;

import euler.Problem;
import euler.sequence.Primes;

public class Problem187 extends Problem<Integer> {
    private static final long LIMIT = (long) Math.pow(10, 8);
    private static final long SQRT_LIMIT = (long) Math.sqrt(LIMIT);

    @Override
    public Integer solve() {
        Primes primes1 = new Primes();
        Primes primes2 = new Primes();

        int count = 0;
        for (long p1 : primes1) {
            primes2.reset();
            for (long p2 : primes2) {
                if (p2 < p1) {
                    continue;
                } else if (p1 * p2 >= LIMIT) {
                    break;
                }

                count++;
            }
            if (p1 > SQRT_LIMIT) {
                break;
            }
        }

        return count;
    }
}
