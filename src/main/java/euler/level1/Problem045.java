package euler.level1;

import euler.IntegerProblem;
import euler.sequence.HexagonalNumbers;
import euler.sequence.LongSequence;
import euler.sequence.PentagonalNumbers;
import euler.sequence.TriangleNumbers;

public class Problem045 extends IntegerProblem {
    @Override
    public long solve() {
        final LongSequence tria = new TriangleNumbers();
        final LongSequence pent = new PentagonalNumbers();
        final LongSequence hexa = new HexagonalNumbers();

        long t = tria.next();
        long p = pent.next();
        long h = hexa.next();
        while (true) {
            if (t == p && t == h) {
                if (t <= 40755) {
                    h = hexa.next();
                } else {
                    return t;
                }
            } else if (p > h) {
                h = hexa.next();
            } else if (t > p) {
                p = pent.next();
            } else {
                t = tria.next();
            }
        }
    }
}
