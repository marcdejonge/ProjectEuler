package euler.level5;

import java.util.stream.LongStream;

import euler.IntegerProblem;

public class Problem206 extends IntegerProblem {

    private static final long MIN = (long) Math.sqrt(1020304050607080900L);
    private static final long MAX = (long) Math.sqrt(1929394959697989990L);
    private final int[] CHECK_NUMBERS = new int[] { 0, 9, 8, 7, 6, 5, 4, 3, 2, 1 };

    public Problem206() {
    }

    @Override
    public long solve() throws Exception {
        return LongStream.rangeClosed(MIN, MAX)
                         .parallel()
                         .filter(nr -> {
                             long square = nr * nr;
                             for (final int a : CHECK_NUMBERS) {
                                 if (square % 10 != a) {
                                     return false;
                                 }
                                 square /= 100;
                             }
                             return true;
                         }).findAny().getAsLong();
    }
}
