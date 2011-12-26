package euler.level2;

import euler.Problem;
import euler.Triplet;
import euler.sequence.PythagoreanTriplets;

public class Problem075 extends Problem<Integer> {
    private final static int MAX = 1500000;

    @Override
    public Integer solve() {
        final int[] nrs = new int[MAX + 1];
        final PythagoreanTriplets pts = new PythagoreanTriplets();

        int lastSum = 0;
        for (Triplet t = pts.next(); lastSum < t.getSum() || t.getSum() < MAX; t = pts.next()) {
            final int sum = t.getSum();
            for (int ix = sum; ix <= MAX; ix += sum) {
                nrs[ix]++;
            }
            lastSum = sum;
        }

        int cnt = 0;
        for (int ix = 0; ix < MAX; ix++) {
            if (nrs[ix] == 1) {
                cnt++;
            }
        }
        return cnt;
    }
}
