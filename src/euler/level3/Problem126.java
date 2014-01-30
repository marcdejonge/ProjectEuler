package euler.level3;

import euler.Problem;

public class Problem126 extends Problem<Integer> {
    private static int layer(int x, int y, int z, int n) {
        return 2 * (x * y + x * z + y * z) + 4 * (x + y + z + n - 2) * (n - 1);
    }

    @Override
    public Integer solve() {
        int[] count = new int[20000];
        boolean changedForX = true;
        for (int x = 1; changedForX; x++) {
            changedForX = false;
            boolean changedForY = true;
            for (int y = 1; changedForY && y <= x; y++) {
                changedForY = false;
                boolean changedForZ = true;
                for (int z = 1; changedForZ && z <= y; z++) {
                    changedForZ = false;
                    for (int n = 1; layer(x, y, z, n) < count.length; n++) {
                        count[layer(x, y, z, n)]++;
                        changedForX = changedForY = changedForZ = true;
                    }
                }
            }
        }

        for (int ix = 0; ix < count.length; ix++) {
            if (count[ix] == 1000) {
                return ix;
            }
        }

        return null;
    }
}
