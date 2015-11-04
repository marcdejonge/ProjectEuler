package euler.level3;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import euler.IntegerProblem;
import euler.sequence.PythagoreanTriplets;

public class Problem139 extends IntegerProblem {

    private static final int LIMIT = 100_000_000;

    @Override
    public long solve() throws Exception {
        AtomicInteger counter = new AtomicInteger();
        Stream.generate(new PythagoreanTriplets()).mapToInt(tuple -> {
            if (tuple.c % (tuple.b - tuple.a) == 0) {
                int count = (LIMIT - 1) / tuple.getSum();
                print("%s -> %d%n", tuple, count);
                counter.addAndGet(count);
            }
            return tuple.getSum();
        }).allMatch(sum -> sum < LIMIT + LIMIT / 3);
        return counter.longValue();
    }
}
