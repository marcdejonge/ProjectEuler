package euler.level3;

import euler.IntegerProblem;
import euler.sequence.SumDigitsPowerSequence;

public class Problem119 extends IntegerProblem {

    @Override
    public long solve() {
        SumDigitsPowerSequence seq = new SumDigitsPowerSequence();
        for (int ix = 1; ix < 30; ix++) {
            seq.next();
        }
        return seq.next();
    }

}
