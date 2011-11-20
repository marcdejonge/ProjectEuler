package euler.level2;

import euler.Problem;
import euler.sequence.TriangleNumbers;

public class Problem085 extends Problem<Long> {

    private static final int TARGET = 2000000;

    @Override
    public Long solve() {
        long closest = 0;
        long bestX = 0, bestY = 0;

        final TriangleNumbers outerNumbers = new TriangleNumbers();
        final TriangleNumbers innerNumber = new TriangleNumbers();
        for (final long x : outerNumbers) {
            if (x >= TARGET) {
                break;
            }

            innerNumber.reset();
            for (final long y : innerNumber) {
                if (y >= x) {
                    break;
                }

                final long nr = x * y;
                if (Math.abs(TARGET - nr) < Math.abs(TARGET - closest)) {
                    closest = nr;
                    bestX = outerNumbers.position();
                    bestY = innerNumber.position();
                }

                if (nr > TARGET) {
                    break;
                }
            }
        }

        return bestX * bestY;
    }

}
