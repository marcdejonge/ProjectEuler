package euler.level2;

import euler.Problem;
import euler.collection.IntHashSet;
import euler.sequence.DivisorsSequence;

public class Problem088 extends Problem<Long> {

    private static final int MIN = 2;
    private static final int MAX = 12000;

    private boolean findPossibleK(int nr, int[] divs, int divIx, int added, int multiplied, int k, IntHashSet foundK) {
        if (nr == multiplied) {
            k += nr - added;
            if (k >= MIN && k <= MAX && !foundK.contains(k)) {
                foundK.add(k);
                return true;
            } else {
                return false;
            }
        } else if (nr > multiplied && divIx < divs.length) {
            int curr = divs[divIx];

            boolean result = false;
            result |= findPossibleK(nr, divs, divIx + 1, added, multiplied, k, foundK);
            if (curr > 1) {
                result |= findPossibleK(nr, divs, divIx, added + curr, multiplied * curr, k + 1, foundK);
            }
            return result;
        } else {
            return false;
        }
    }

    private boolean findPossibleK(int nr, IntHashSet foundK) {
        int[] divs = DivisorsSequence.properDivisors(nr);
        return divs.length > 1 && findPossibleK(nr, divs, 0, 0, 1, 0, foundK);
    }

    @Override
    public Long solve() {
        long total = 0;
        IntHashSet foundK = new IntHashSet(15);
        for (int nr = 4; foundK.size() <= MAX - MIN; nr++) {
            if (findPossibleK(nr, foundK)) {
                total += nr;
            }
        }

        return total;
    }
}
