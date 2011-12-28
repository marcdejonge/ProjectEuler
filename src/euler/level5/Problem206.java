package euler.level5;

import euler.MultiCoreProblem;
import euler.sequence.NaturalNumbers;

public class Problem206 extends MultiCoreProblem {

    private static final long MIN = (long) Math.sqrt(1020304050607080900L);
    private static final long MAX = (long) Math.sqrt(1929394959697989990L);
    private final int[] CHECK_NUMBERS = new int[] { 0, 9, 8, 7, 6, 5, 4, 3, 2, 1 };

    public Problem206() {
        super(new NaturalNumbers(MIN), 10000);
    }

    @Override
    public boolean handleNumber(long nr) {
        if (nr > MAX) {
            return false;
        }

        long square = nr * nr;
        for (final int a : CHECK_NUMBERS) {
            if (square % 10 != a) {
                return true;
            }
            square /= 100;
        }

        result.set(nr);
        return false;
    }

}
