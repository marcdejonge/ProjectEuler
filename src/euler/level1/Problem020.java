package euler.level1;

import euler.Problem;
import euler.numberic.Number;

public class Problem020 extends Problem<Integer> {

    @Override
    public Integer solve() {
        Number fact = Number.ONE;
        for (int i = 2; i <= 100; i++) {
            fact = fact.multiply(Number.valueOf(i));
        }
        return fact.digitalSum();
    }
}
