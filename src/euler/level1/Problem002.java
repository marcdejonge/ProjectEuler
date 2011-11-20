package euler.level1;

import euler.Problem;

public class Problem002 extends Problem<Integer> {

    @Override
    public Integer solve() {
        int prev1 = 1, prev2 = 2;
        int sum = 2;
        int next = 0;
        do {
            next = prev1 + prev2;
            if ((next & 1) == 0) {
                sum += next;
            }
            prev1 = prev2;
            prev2 = next;
        } while (next < 4000000);
        return sum;
    }

}
