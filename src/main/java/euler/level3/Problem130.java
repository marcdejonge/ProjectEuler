package euler.level3;

import static euler.level3.Problem129.A;
import euler.IntegerProblem;
import euler.sequence.Primes;

public class Problem130 extends IntegerProblem {
    @Override
    public long solve() {
        int count = 0, sum = 0;
        for (int n = 3; count < 25; n += 2) {
            if (n % 5 != 0 && !Primes.isPrime(n)) {
                if ((n - 1) % A(n) == 0) {
                    count++;
                    sum += n;
                }
            }
        }
        return sum;
    }
}
