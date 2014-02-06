package euler.level3;

import java.util.ArrayList;
import java.util.List;

import euler.IntegerProblem;
import euler.sequence.Primes;

public class Problem111 extends IntegerProblem {

    private void generateList(int length, long number, int digitsNeeded, int digit, List<Long> primes) {
        if (length == 0) {
            if (Primes.isPrime(number)) {
                primes.add(number);
            }
        } else if (length == digitsNeeded) {
            generateList(length - 1, number * 10 + digit, digitsNeeded - 1, digit, primes);
        } else if (length > digitsNeeded) {
            for (int d = 0; d < 10; d++) {
                if (d != 0 || number != 0) {
                    generateList(length - 1, number * 10 + d, digitsNeeded - (d == digit ? 1 : 0), digit, primes);
                }
            }
        }
    }

    @Override
    public long solve() {
        long result = 0;
        final List<Long> primes = new ArrayList<Long>();

        final int length = 10;

        for (int d = 0; d < 10; d++) {
            for (int digitsNeeded = length; digitsNeeded > 0; digitsNeeded--) {
                primes.clear();
                generateList(length, 0, digitsNeeded, d, primes);
                if (!primes.isEmpty()) {
                    long sum = 0;
                    for (long prime : primes) {
                        sum += prime;
                    }
                    // System.out.printf("S(%d,%d) = %d%n", length, d, sum);
                    // System.out.println(primes);
                    result += sum;
                    break;
                }
            }
        }

        return result;
    }
}
