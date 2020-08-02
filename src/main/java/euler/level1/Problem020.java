package euler.level1;

import euler.IntegerProblem;
import euler.numberic.Number;

public class Problem020 extends IntegerProblem {
    @Override
    public long solve() {
        Number fact = Number.ONE;
        for (int i = 2; i <= 100; i++) {
            fact = fact.multiply(Number.valueOf(i));
        }
        return fact.digitalSum();
    }
}
