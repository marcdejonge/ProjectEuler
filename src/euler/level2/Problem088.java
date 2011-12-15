package euler.level2;

import java.util.SortedMap;
import java.util.TreeMap;

import euler.Problem;
import euler.sequence.DivisorsSequence;

public class Problem088 extends Problem<Long> {

    private static final int MIN = 2;
    private static final int MAX = 12000;

    private boolean findPossibleK(int nr, int[] divs, int divsIx, int added, int k, SortedMap<Integer, Integer> solutions) {
        // System.out.printf("%d -> %d & %d%n", divsIx, added, multiplied);
        while (divsIx < divs.length && divs[divsIx] == 0) {
            divsIx++;
        }

        if (divsIx >= divs.length) {
            k = k + nr - added;
            if (k >= MIN && k <= MAX && !solutions.containsKey(k)) {
                solutions.put(k, nr);
                // System.out.println(nr + " -> " + k);
                return true;
            } else {
                return false;
            }
        } else {
            return findPossibleKHelper(nr, divs, divsIx, added, k, solutions, divs[divsIx], divsIx + 1);
        }
    }

    private boolean findPossibleK(int nr, SortedMap<Integer, Integer> solutions) {
        int[] divs = DivisorsSequence.divisors(nr);
        return divs.length > 1 && findPossibleK(nr, divs, 0, 0, 0, solutions);
    }

    private boolean findPossibleKHelper(int nr,
                                        int[] divs,
                                        int divsIx,
                                        int added,
                                        int k,
                                        SortedMap<Integer, Integer> solutions,
                                        int multiplied,
                                        int nextIx) {
        while (nextIx < divs.length && divs[nextIx] == 0) {
            nextIx++;
        }

        if (nextIx >= divs.length) {
            return findPossibleK(nr, divs, divsIx + 1, added + multiplied, k + 1, solutions);
        } else {
            boolean result = findPossibleKHelper(nr, divs, divsIx, added, k, solutions, multiplied, nextIx + 1);
            int temp = divs[nextIx];
            divs[nextIx] = 0;
            result |= findPossibleKHelper(nr, divs, divsIx, added, k, solutions, multiplied * temp, nextIx + 1);
            divs[nextIx] = temp;
            return result;
        }
    }

    private int minNr(int k) {
        return minNr(k, 0, 0, 1, k, k * 2);
    }

    private int minNr(int k, int ix, int sum, int product, int lastNr, int minNr) {
        for (int nr = Math.min(lastNr, minNr / product); nr >= 2; nr--) {
            int newProd = product * nr;
            int newSum = sum + nr;

            int totalSum = newSum + k - (ix + 1);

            if (totalSum == newProd) {
                if (newProd < minNr) {
                    minNr = newProd;
                }
            } else {
                int newNr = minNr(k, ix + 1, newSum, newProd, nr, minNr);
                if (newNr < minNr) {
                    minNr = newNr;
                }
            }
        }
        return minNr;
    }

    @Override
    public Long solve() {
        long total = 0;
        SortedMap<Integer, Integer> solutions = new TreeMap<Integer, Integer>();
        for (int nr = 4; solutions.size() <= MAX - MIN; nr++) {
            if (findPossibleK(nr, solutions)) {
                total += nr;
            }
        }

        // Set<Integer> nrs = new HashSet<Integer>();
        // for (int k = MIN; k <= MAX; k++) {
        // int nr = minNr(k);
        // nrs.add(nr);
        // System.out.printf("%d -> %d / %d%n", k, nr, solutions.get(k));
        // }
        // total = 0;
        // for (int nr : nrs) {
        // total += nr;
        // }

        // for (Entry<Integer, Integer> e : solutions.entrySet()) {
        // System.out.printf("%d -> %d%n", e.getKey(), e.getValue());
        // }

        return total;
    }
}
