package euler.sequence;

import java.util.LinkedList;
import java.util.Queue;
import java.util.function.BiFunction;

// See problem 387
public class HarshadNumbers extends AbstractSequence {

    private final Queue<euler.Pair<Long, Integer>> harshadNumbers = new LinkedList<>();
    private final BiFunction<Long, Integer, Boolean> extraCheck;
    private long pos = 0;
    private long curr = 0;

    public HarshadNumbers(BiFunction<Long, Integer, Boolean> extraCheck) {
        this.extraCheck = extraCheck;
        for (int ix = 1; ix < 10; ix++) {
            harshadNumbers.add(euler.Pair.from(Long.valueOf(ix), ix));
        }
    }

    @Override
    public long current() {
        return curr;
    }

    @Override
    public long next() {
        while (!harshadNumbers.isEmpty()) {
            euler.Pair<Long, Integer> prev = harshadNumbers.poll();
            long prevNr = prev.getFirst();
            int sumDigits = prev.getSecond();

            for (int ix = 0; ix < 10; ix++) {
                long newNr = prevNr * 10 + ix;
                int newSumDigits = sumDigits + ix;
                if (newNr > 0 && newNr / newSumDigits * newSumDigits == newNr) {
                    harshadNumbers.add(euler.Pair.from(newNr, newSumDigits));
                }
            }

            curr = prevNr;
            pos++;

            if (extraCheck == null || extraCheck.apply(prevNr, sumDigits)) {
                return curr;
            }
        }

        throw new IllegalStateException("No more numbers available!");
    }

    @Override
    public long position() {
        return pos;
    }
}
