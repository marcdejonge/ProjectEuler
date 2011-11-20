package euler.level1;

import euler.Problem;

public class Problem004 extends Problem<Integer> {

    @Override
    public Integer solve() {
        for (int x = 9; x >= 0; x--) {
            for (int y = 9; y >= 0; y--) {
                for (int z = 9; z >= 0; z--) {
                    final int nr = x * 100001 + y * 10010 + z * 1100;
                    for (int div = 999; div > 100; div--) {
                        if (nr % div == 0) {
                            final int mul = nr / div;
                            if (mul < 1000) {
                                if (mul < 100) {
                                    break;
                                } else {
                                    return nr;
                                }
                            }

                        }
                    }
                }
            }
        }
        return -1;
    }

}
