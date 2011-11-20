package euler.level2;

import euler.Problem;
import euler.numberic.Number;

public class Problem063 extends Problem<Integer> {
    @Override
    public Integer solve() {
        int count = 0;
        for (int i = 1; i < 100; i++) {
            for (long base = 1; base < 100; base++) {
                final Number nr = Number.valueOf(base).pow(i);
                if (nr.getFilledLength() == i) {
                    count++;
                } else if (nr.getFilledLength() > i) {
                    break;
                }
            }
        }
        return count;
    }
}
