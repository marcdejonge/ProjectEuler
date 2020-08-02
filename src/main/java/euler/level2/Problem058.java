package euler.level2;

import euler.IntegerProblem;
import euler.sequence.Primes;

public class Problem058 extends IntegerProblem {
    @Override
    public long solve() {
        int nrValues = 1, nrPrimes = 0;
        int currValue = 1, currAdd = 2;
        while (nrPrimes == 0 || nrValues < nrPrimes) {
            for (int i = 0; i < 4; i++) {
                currValue += currAdd;
                nrValues++;
                if (Primes.isPrime(currValue)) {
                    nrPrimes += 10;
                }
            }
            currAdd += 2;
        }
        // System.out.println(currValue);
        return currAdd - 1;
    }
}
