package euler.level2;

import euler.IntegerProblem;
import euler.Pair;
import euler.numberic.Number;
import euler.sequence.ContinuedFraction;
import euler.sequence.SquareDigits;

public class Problem066 extends IntegerProblem {
    @Override
    public long solve() {
        Number maxX = Number.ZERO;
        int maxD = 0;
        int squareN = 2, squared = 4;
        for (int d = 2; d <= 1000; d++) {
            if (d == squared) {
                squareN++;
                squared = squareN * squareN;
            } else {
                final Number _d = Number.valueOf(d);
                final ContinuedFraction est = new ContinuedFraction(new SquareDigits(d));

                Pair<Number, Number> pair = est.next();
                while (true) {
                    final Number diff = pair.getFirst().pow(2).subtract(_d.multiply(pair.getSecond().pow(2)));
                    if (diff.equals(Number.ONE)) {
                        break;
                    }
                    pair = est.next();
                }

                print("Found %s^2 - %d * %s^2 = 1%n", pair.getFirst(), d, pair.getSecond());
                if (maxX.compareTo(pair.getFirst()) < 0) {
                    maxX = pair.getFirst();
                    maxD = d;
                }
            }
        }
        return maxD;
    }
}
