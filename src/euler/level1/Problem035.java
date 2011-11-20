package euler.level1;

import euler.Problem;
import euler.sequence.Primes;

public class Problem035 extends Problem<Integer> {
    private long rotate(long value, long powerTen) {
        long res = value * 10 % powerTen;
        res += value * 10 / powerTen;
        return res;
    }

    @Override
    public Integer solve() {
        final Primes p = new Primes();

        int count = 0;
        int powerTen = 10;
        for (long prime = p.next(); prime < 1000000; prime = p.next()) {
            if (prime >= powerTen) {
                powerTen *= 10;
            }

            long rot = rotate(prime, powerTen);
            boolean allPrime = true;
            while (rot != prime && allPrime) {
                if (!Primes.isPrime(rot)) {
                    allPrime = false;
                }
                rot = rotate(rot, powerTen);
            }

            if (allPrime) {
                count++;
            }
        }
        return count;
    }
}
