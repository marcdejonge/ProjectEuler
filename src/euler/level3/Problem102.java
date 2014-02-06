package euler.level3;

import java.io.IOException;
import java.util.List;

import euler.IntegerProblem;
import euler.input.FileUtils;
import euler.twoD.Triangle;
import euler.twoD.Vector;

public class Problem102 extends IntegerProblem {
    @Override
    public long solve() throws IOException {
        List<Triangle> ts;
        ts = Triangle.read(FileUtils.findFile(this));

        int total = 0;
        final Vector orig = new Vector(0, 0);

        for (final Triangle t : ts) {
            if (t.containsPoint(orig)) {
                total++;
            }
        }
        return total;
    }
}
