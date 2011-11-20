package euler.level1;

import java.util.Arrays;

import euler.Problem;
import euler.sequence.Primes;

public class Problem046 extends Problem<Integer> {

    @Override
    public Integer solve() {

        final Primes primes = new Primes();
        final long[] dsquare = new long[1000];
        for (int i = 1; i < dsquare.length; i++) {
            dsquare[i] = 2 * i * i;
        }

        loop: for (int comp = 3;; comp += 2) {
            if (!Primes.isPrime(comp)) {
                primes.reset();
                for (long prime = primes.next(); prime < comp; prime = primes.next()) {
                    if (Arrays.binarySearch(dsquare, comp - prime) > 0) {
                        continue loop;
                    }
                }
                return comp;
            }
        }
    }
}
