package euler.level2;

import java.util.stream.IntStream;

import euler.IntegerProblem;

public class Problem092 extends IntegerProblem {

    private static final int MAX = 10000000;

    private static int squareOfDigits(int value) {
        int result = 0;
        while (value > 0) {
            final int x = value % 10;
            result += x * x;
            value /= 10;
        }
        return result;
    }

    @Override
    public long solve() throws Exception {
        return IntStream.range(1, MAX)
                        .parallel()
                        .filter(nr -> {
                            while (nr != 1 && nr != 89) {
                                nr = squareOfDigits(nr);
                            }
                            return nr == 89;
                        })
                        .count();
    }
}
