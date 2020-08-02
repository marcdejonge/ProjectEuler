package euler.level4;

import java.util.HashMap;
import java.util.Map;

import euler.IntegerProblem;
import euler.Triplet;

public class Problem164 extends IntegerProblem {

    private final Map<Triplet, Long> solutions;
    private final int startSize;

    public Problem164() {
        this(20);
    }

    public Problem164(int size) {
        solutions = new HashMap<Triplet, Long>();
        startSize = size;
    }

    private long getSolution(int digitsLeft, int prevDigit, int prev2Digit) {
        Triplet key = new Triplet(digitsLeft, prevDigit, prev2Digit);
        if (digitsLeft == 1) {
            return Math.max(0, 9 - prevDigit - prev2Digit + 1);
        } else if (solutions.containsKey(key)) {
            return solutions.get(key);
        } else {
            long sum = 0;
            for (int digit = 0; digit + prevDigit + prev2Digit <= 9; digit++) {
                sum += getSolution(digitsLeft - 1, digit, prevDigit);
            }
            solutions.put(key, sum);
            return sum;
        }
    }

    @Override
    public long solve() {
        return getSolution(startSize, 0, 0) - getSolution(startSize - 1, 0, 0);
    }

}
