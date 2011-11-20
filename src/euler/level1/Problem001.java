package euler.level1;

import euler.Problem;

public class Problem001 extends Problem<Integer> {
    @Override
    public Integer solve() {
        int sum = 0;
        for (int i = 1; i < 1000; i++) {
            if (i % 3 == 0 || i % 5 == 0) {
                sum += i;
            }
        }
        return sum;
    }
}
