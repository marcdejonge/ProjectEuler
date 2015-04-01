package euler.level3;

import java.math.BigInteger;
import java.util.function.BinaryOperator;
import java.util.function.LongSupplier;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import euler.BigIntegerProblem;
import euler.sequence.FunctionGenerator;

public class Problem138 extends BigIntegerProblem {

    static class HistoryGenerator implements Supplier<BigInteger> {
        private BigInteger n1, n2;
        private final BinaryOperator<BigInteger> op;

        public HistoryGenerator(long start1, long start2, BinaryOperator<BigInteger> op) {
            n1 = BigInteger.valueOf(start1);
            n2 = BigInteger.valueOf(start2);
            this.op = op;
        }

        @Override
        public BigInteger get() {
            BigInteger value = op.apply(n1, n2);
            n1 = n2;
            n2 = value;
            return value;
        }
    }

    static class LengthGenerator implements LongSupplier {
        FunctionGenerator yGen = new FunctionGenerator(1, 0, 0);
        FunctionGenerator xPlusGen = new FunctionGenerator(5, 4, 1);
        FunctionGenerator xMinGen = new FunctionGenerator(5, -4, 1);

        long y = yGen.next();
        long xp = xPlusGen.next();
        long xm = xMinGen.next();

        @Override
        public long getAsLong() {
            while (true) {
                if (y == xp || y == xm) {
                    System.out.printf("%d^2 + %d^2%n", yGen.position(), y == xp ? xPlusGen.position() : xMinGen.position());
                    long result = yGen.position();
                    y = yGen.next();
                    return result;
                } else if (xp < y) {
                    xp = xPlusGen.next();
                } else if (xm < y) {
                    xm = xMinGen.next();
                } else {
                    y = yGen.next();
                }
            }
        }
    }

    /**
     * b = 2x (x is half of the base), y is the sloped length
     *
     * h = sqrt(y^2 - (b/2)^2) = sqrt(y^2 - x^2)
     *
     * h = b±1 --> (2x±1)^2 = y^2 - x^2 --> 4x^2 ± 4x + 1 = y^2 - x^2
     *
     * -> y^2 = 5x^2 ± 4x + 1
     *
     * Now we have to find values for x and y that match and we have a solution
     *
     * After finding the first solutions (see the LengthGenerator), the pattern seemed to be 18*n2 - n1
     */
    @Override
    public BigInteger solve() {
        return Stream.generate(new HistoryGenerator(1, 1, (x, y) -> y.multiply(BigInteger.valueOf(18)).subtract(x)))
                .limit(12)
                .collect(Collectors.reducing((x, y) -> x.add(y)))
                .get();
    }
}