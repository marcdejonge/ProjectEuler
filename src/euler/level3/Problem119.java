package euler.level3;

import euler.Problem;
import euler.sequence.SumDigitsPowerSequence;

public class Problem119 extends Problem<Long> {

    @Override
    public Long solve() {
        SumDigitsPowerSequence seq = new SumDigitsPowerSequence();
        for (int ix = 1; ix < 30; ix++) {
            seq.next();
        }
        return seq.next();
    }

}
