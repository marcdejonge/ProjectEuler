package euler.level3;

import euler.Problem;
import euler.sequence.Primes;

public class Problem128 extends Problem<Long> {
    private static final int LIMIT = 2000;

    @Override
    public Long solve() {
        int found = 0;
        // For each ring try test the start and end. Ring 1 start with 2 and ends with 7
        for (long ringStart = 2, ringEnd = 7, ringSize = 6;; ringStart += ringSize, ringSize += 6, ringEnd += ringSize) {
            // Both need the difference between start and end to be prime
            if (Primes.isPrime(ringSize - 1)) {
                // Do the checks for the starting node
                // Check left-up (diff= ringSize + 1) and right-up (diff= ringSize + (ringSize + 6) - 1)
                if (Primes.isPrime(ringSize + 1) && Primes.isPrime(2 * ringSize + 5)) {
                    if (++found == LIMIT) {
                        return ringStart;
                    }
                }

                // Do the checks for the ending node
                // Check left-down (diff= (ringSize - 1) + (ringSize - 6)) and right-up (diff= (ringSize + 6) - 1)
                if (Primes.isPrime(2 * ringSize - 7) && Primes.isPrime(ringSize + 5)) {
                    if (++found == LIMIT) {
                        return ringEnd;
                    }
                }
            }
        }
    }
}
