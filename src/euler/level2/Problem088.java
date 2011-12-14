package euler.level2;

import java.util.HashSet;
import java.util.Set;

import euler.Problem;
import euler.sequence.DivisorsSequence;

public class Problem088 extends Problem<Long> {

    private static final int MIN = 2;
    private static final int MAX = 12000;

    private boolean findPossibleK(int nr, int[] divs, int divsIx, int added, int multiplied, int k, Set<Integer> ksFound) {
        if (divsIx >= divs.length) {
            if (multiplied > 1) {
                added = added + multiplied;
                k++;
            }

            k = k + nr - added;
            if (k >= MIN && k <= MAX && !ksFound.contains(k)) {
                ksFound.add(k);
                // System.out.println(nr + " -> " + k);
                return true;
            } else {
                return false;
            }
        } else {
            int nextDiv = divs[divsIx];
            return findPossibleK(nr, divs, divsIx + 1, added, multiplied * nextDiv, k, ksFound) | findPossibleK(nr,
                                                                                                                divs,
                                                                                                                divsIx + 1,
                                                                                                                added + multiplied,
                                                                                                                nextDiv,
                                                                                                                k + 1,
                                                                                                                ksFound);
        }
    }

    @Override
    public Long solve() {
        Set<Integer> solutions = new HashSet<Integer>();
        long total = 0;
        for (int nr = 4; solutions.size() <= MAX - MIN; nr++) {
            int[] divs = DivisorsSequence.divisors(nr);
            if (divs.length > 1) {
                if (findPossibleK(nr, divs, 1, 0, divs[0], 0, solutions)) {
                    total += nr;
                }
            }
        }

        return total;
    }
}
