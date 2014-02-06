package euler.level4;

import java.util.Set;

import euler.IntegerProblem;
import euler.collection.HashSet;
import euler.sequence.AbstractSequence;
import euler.twoD.Line;
import euler.twoD.Point;

public class Problem165 extends IntegerProblem {

    private static class BlumBlumShub extends AbstractSequence {
        private static final int START = 290797;
        private static final int MOD = 500;
        private static final int GENMOD = 50515093;

        private long s;
        private long curr;
        private int pos;

        public BlumBlumShub() {
            reset();
        }

        @Override
        public long current() {
            return curr;
        }

        @Override
        public long next() {
            s = s * s % GENMOD;
            curr = s % MOD;
            return curr;
        }

        @Override
        public long position() {
            return pos;
        }

        @Override
        public void reset() {
            s = START;
            curr = s % MOD;
            pos = 0;
        }
    }

    private final Line[] lines;

    public Problem165() {
        lines = new Line[5000];
        final BlumBlumShub bbs = new BlumBlumShub();
        for (int ix = 0; ix < lines.length; ix++) {
            lines[ix] = new Line((int) bbs.next(), (int) bbs.next(), (int) bbs.next(), (int) bbs.next());
        }
    }

    @Override
    public long solve() {
        Set<Point> points = HashSet.create(Point.class, 22);
        for (int ix = 0; ix < lines.length; ix++) {
            for (int jx = ix + 1; jx < lines.length; jx++) {
                Point p = lines[ix].getIntersection(lines[jx]);
                if (p != null) {
                    points.add(p);
                }
            }
        }
        return points.size();
    }
}
