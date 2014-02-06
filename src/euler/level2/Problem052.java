package euler.level2;

import euler.IntegerProblem;
import euler.numberic.Number;

public class Problem052 extends IntegerProblem {
    @Override
    public long solve() {
        for (int i = 100000;; i++) {
            final Number n1 = Number.valueOf(i);
            final Number sort = n1.sort();
            final Number n2 = n1.add(n1);
            if (n2.sort().equals(sort)) {
                final Number n3 = n2.add(n1);
                if (n3.sort().equals(sort)) {
                    final Number n4 = n3.add(n1);
                    if (n4.sort().equals(sort)) {
                        final Number n5 = n4.add(n1);
                        if (n5.sort().equals(sort)) {
                            final Number n6 = n5.add(n1);
                            if (n6.sort().equals(sort)) {
                                return n1.longValue();
                            }
                        }
                    }
                }
            }
        }
    }
}
