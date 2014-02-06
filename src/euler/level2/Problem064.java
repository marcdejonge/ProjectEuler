package euler.level2;

import euler.IntegerProblem;
import euler.sequence.SquareDigits;

public class Problem064 extends IntegerProblem {
    @Override
    public long solve() {
        int count = 0;
        for (int i = 2; i < 10000; i++) {
            final SquareDigits list = new SquareDigits(i);
            if (list.getSquareN() * list.getSquareN() == i) {
                continue;
            }

            final SquareDigits list2 = new SquareDigits(i);
            list.next();
            list.next();
            list2.next();
            list2.next();
            list2.next();
            int length = 1;
            while (!list.equalPoint(list2)) {
                list2.next();
                length++;
            }

            if ((length & 1) == 1) {
                count++;
            }
        }

        return count;
    }
}
