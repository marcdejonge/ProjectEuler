package euler.level2;

import euler.IntegerProblem;
import euler.numberic.Number;
import euler.sequence.Primes;

public class Problem051 extends IntegerProblem {
    @Override
    public long solve() {
        final Primes primes = new Primes();
        final byte BASE = 1;

        for (long prime = primes.next();; prime = primes.next()) {
            final Number nr = Number.valueOf(prime);
            if (nr.contains(BASE)) {
                // System.out.print(nr);
                int count = 1;
                for (byte replace = BASE + 1; replace < 10; replace++) {
                    final long newNr = nr.replace(BASE, replace).longValue();
                    // System.out.print(" " + newNr);
                    if (Primes.isPrime(newNr)) {
                        count++;
                        // System.out.print(" YES");
                    }
                }
                // System.out.println(" " + count + " yesses");
                if (count == 8) {
                    return prime;
                }
            }
        }
    }
}
