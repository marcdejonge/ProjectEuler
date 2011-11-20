package euler.level1;

import java.util.LinkedList;
import java.util.Queue;

import euler.Problem;
import euler.sequence.Primes;

public class Problem050 extends Problem<Long> {

    @Override
    public Long solve() {
        final Primes gen = new Primes();

        final Queue<Long> primes = new LinkedList<Long>();
        long sum = 0;
        long prime = gen.next();
        while (sum + prime < 1000000) {
            primes.add(prime);
            sum += prime;
            prime = gen.next();
        }

        while (!Primes.isPrime(sum)) {
            // System.out.println(sum + " = " + primes);

            if (sum + prime < 1000000) {
                sum += prime;
                primes.add(prime);
                prime = gen.next();
            } else {
                sum -= primes.remove();
            }
        }

        return sum;
    }
}
