package euler.level8;

import java.util.Arrays;

import euler.IntegerProblem;
import euler.sequence.HarshadNumbers;
import euler.sequence.Primes;

public class Problem387 extends IntegerProblem {

    @Override
    public long solve() throws Exception {
        long[] nrs = new HarshadNumbers((x, y) -> Primes.isPrime(x / y)).head(10_000_000_000_000L);
        return Arrays.stream(nrs).map(nr -> {
            long sum = 0;
            long posPrime = nr * 10 + 1;
            if (Primes.isPrime(posPrime)) {
                println(posPrime);
                sum += posPrime;
            }
            posPrime += 2; // End with a 3
            if (Primes.isPrime(posPrime)) {
                println(posPrime);
                sum += posPrime;
            }
            posPrime += 4; // End with a 7
            if (Primes.isPrime(posPrime)) {
                println(posPrime);
                sum += posPrime;
            }
            posPrime += 2; // End with a 9
            if (Primes.isPrime(posPrime)) {
                println(posPrime);
                sum += posPrime;
            }
            return sum;
        }).sum();
    }
}
