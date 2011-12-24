package euler.level3;

import euler.Problem;

public class Problem104 extends Problem<Integer> {

    private boolean is9(int nr) {
        if (nr < 100000000 || nr > 1000000000) {
            return false;
        } else {
            int nrsFound = 0;
            while (nr > 0) {
                nrsFound |= 1 << nr % 10;
                nr /= 10;
            }
            return nrsFound == 0x3FE;
        }
    }

    @Override
    public Integer solve() {
        final int mod = 1000000000;
        int lastF1 = 1, lastF2 = 1;

        final long lMod = 1000000000000000000L;
        long firstF1 = 1, firstF2 = 1;

        int k = 2;
        while (true) {
            int temp = lastF1 + lastF2;
            lastF1 = lastF2;
            lastF2 = temp % mod;

            long lTemp = firstF1 + firstF2;
            firstF1 = firstF2;
            firstF2 = lTemp;
            while (firstF2 > lMod) {
                firstF1 /= 10;
                firstF2 /= 10;
            }

            k++;

            if (is9(lastF2) && is9((int) (firstF2 / mod))) {
                return k;
            }
        }
    }
}
