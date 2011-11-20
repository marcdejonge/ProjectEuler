package euler.level2;

import euler.Problem;

public class Problem074 extends Problem<Integer> {
    private final static int MAX = 1000000;

    private final int[] facts;

    private final int[] lengths;

    public Problem074() {
        facts = new int[10];
        facts[0] = 1;
        for (int x = 1; x < 10; x++) {
            facts[x] = facts[x - 1] * x;
        }

        lengths = new int[MAX * 3];

        // Some hardcoded loops: (1), (2), (145), (872, 45361), (873, 45362), (169, 163601, 1454), (40585)
        lengths[1] = 1;
        lengths[2] = 2;
        lengths[145] = 1;
        lengths[169] = 3;
        lengths[872] = 2;
        lengths[873] = 2;
        lengths[1454] = 3;
        lengths[40585] = 1;
        lengths[45361] = 2;
        lengths[45362] = 2;
        lengths[363601] = 3;
    }

    private int length(final int x) {
        if (lengths[x] == 0) {
            // Calculate it!
            lengths[x] = 1 + length(next(x));
        }
        return lengths[x];
    }

    private int next(int x) {
        int result = 0;
        while (x > 0) {
            result += facts[x % 10];
            x /= 10;
        }
        return result;
    }

    @Override
    public Integer solve() {
        int count = 0;
        for (int x = 1; x < MAX; x++) {
            if (length(x) == 60) {
                count++;
            }
        }
        return count;
    }
}
