package euler.level1;

import euler.IntegerProblem;
import euler.sequence.LineSequence;
import euler.sequence.Primes;

public class Problem041 extends IntegerProblem {

    @Override
    public long solve() {
        final LineSequence seq = new LineSequence(new byte[] { 7, 6, 5, 4, 3, 2, 1 });

        while (!Primes.isPrime(seq.toNumber().longValue()) && seq.prev()) {
        }

        return seq.toNumber().longValue();
    }
}
