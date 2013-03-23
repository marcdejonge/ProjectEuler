package euler.level3;

import euler.Problem;
import euler.sequence.CombinedSequence;
import euler.sequence.DecreasingNumbers;
import euler.sequence.IncreasingNumbers;
import euler.sequence.InverseSequence;
import euler.sequence.LongSequence;

public class Problem112 extends Problem<Long> {

    @Override
    public Long solve() {
        LongSequence bouncyNumbers = new InverseSequence(new CombinedSequence(new IncreasingNumbers(), new DecreasingNumbers()));

        while (true) {
            long pos = bouncyNumbers.position();
            long nr = bouncyNumbers.next();

            if (nr <= (pos + 1) * 100 / 99) {
                return nr;
            }
        }
    }
}
