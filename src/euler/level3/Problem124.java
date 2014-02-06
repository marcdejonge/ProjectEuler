package euler.level3;

import java.util.Arrays;

import euler.IntegerProblem;

public class Problem124 extends IntegerProblem {
    private static class Radical implements Comparable<Radical> {
        final int nr;
        int rad;

        Radical(int nr) {
            this.nr = nr;
            rad = 1;
        }

        @Override
        public int compareTo(Radical other) {
            if (rad > other.rad) {
                return 1;
            } else if (rad < other.rad) {
                return -1;
            } else {
                return nr - other.nr;
            }
        }
    }

    private static final int QUERY = 10000;

    private static final int LIMIT = 100000;

    @Override
    public long solve() {
        Radical[] radicals = new Radical[LIMIT + 1];
        for (int ix = 0; ix <= LIMIT; ix++) {
            radicals[ix] = new Radical(ix);
        }
        for (int x = 2; x <= LIMIT; x++) {
            if (radicals[x].rad == 1) {
                for (int y = x; y <= LIMIT; y += x) {
                    radicals[y].rad *= x;
                }
            }
        }
        Arrays.sort(radicals);

        return radicals[QUERY].nr;
    }
}
