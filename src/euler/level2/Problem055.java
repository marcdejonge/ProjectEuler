package euler.level2;

import euler.IntegerProblem;
import euler.numberic.Number;

public class Problem055 extends IntegerProblem {
    @Override
    public long solve() {
        int total = 0;
        for (int x = 1; x < 10000; x++) {
            final Number n = Number.valueOf(x);
            Number next = n.addInc(n.reverse());
            int count = 0;
            while (count < 50 && !next.isPalindrome()) {
                next = next.addInc(next.reverse());
                count++;
            }
            if (!next.isPalindrome()) {
                total++;
            }
        }

        return total;
    }
}
