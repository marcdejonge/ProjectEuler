package euler.level1;

import euler.IntegerProblem;
import euler.sequence.AmicableNumbers;

public class Problem021 extends IntegerProblem {
    @Override
    public long solve() {
        final AmicableNumbers nrs = new AmicableNumbers();
        long sum = 0;
        for (long nr = nrs.next(); nr < 10000; nr = nrs.next()) {
            sum += nr;
        }
        return sum;
    }
}
