package euler.level1;

import euler.Problem;
import euler.sequence.LineSequence;
import euler.sequence.Primes;

public class Problem041 extends Problem<Long> {

    @Override
    public Long solve() {
        final LineSequence seq = new LineSequence(new byte[] { 7, 6, 5, 4, 3, 2, 1 });

        while (!Primes.isPrime(seq.toNumber().longValue()) && seq.prev()) {
        }

        return seq.toNumber().longValue();
    }
}
