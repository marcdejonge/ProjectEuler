package euler.level2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import euler.IntegerProblem;
import euler.Pair;
import euler.SolutionNotFoundException;
import euler.sequence.HeptagonalNumbers;
import euler.sequence.HexagonalNumbers;
import euler.sequence.OctagonelNumbers;
import euler.sequence.PentagonalNumbers;
import euler.sequence.SimpleSequence;
import euler.sequence.SquareNumbers;
import euler.sequence.TriangleNumbers;

public class Problem061 extends IntegerProblem {
    private static final int MIN = 1000;
    private static final int MAX = 10000;

    private List<Pair<Integer, SimpleSequence>> calcNrs(SimpleSequence seq) {
        final List<Pair<Integer, SimpleSequence>> result = new ArrayList<Pair<Integer, SimpleSequence>>();
        for (int nr = (int) seq.next(); nr < MAX; nr = (int) seq.next()) {
            if (nr > MIN && nr % 100 >= 10) {
                result.add(Pair.from(nr, seq));
            }
        }
        return result;
    }

    private boolean find(final List<Pair<Integer, SimpleSequence>> nrs,
                         int left,
                         int first,
                         int previous,
                         Map<SimpleSequence, Integer> solution) {
        if (left > 0) {
            final int min = previous <= 0 ? 0 : previous % 100 * 100;
            final int max = previous <= 0 ? Integer.MAX_VALUE : min + 100;

            for (final Pair<Integer, SimpleSequence> nr : nrs) {
                final int n = nr.getFirst();
                if (n >= max) {
                    return false;
                } else if (n >= min && !solution.containsKey(nr.getSecond())) {
                    solution.put(nr.getSecond(), nr.getFirst());
                    if (find(nrs, left - 1, first == 0 ? nr.getFirst() : first, nr.getFirst(), solution)) {
                        return true;
                    }
                    solution.remove(nr.getSecond());
                }
            }

            return false;
        } else {
            return first / 100 == previous % 100;
        }
    }

    @Override
    public long solve() throws SolutionNotFoundException {
        final List<Pair<Integer, SimpleSequence>> nrs = new ArrayList<Pair<Integer, SimpleSequence>>();
        for (final SimpleSequence seq : new SimpleSequence[] { new TriangleNumbers(),
                                                              new SquareNumbers(),
                                                              new PentagonalNumbers(),
                                                              new HexagonalNumbers(),
                                                              new HeptagonalNumbers(),
                                                              new OctagonelNumbers() }) {
            nrs.addAll(calcNrs(seq));
        }
        Collections.sort(nrs, new Comparator<Pair<Integer, SimpleSequence>>() {
            @Override
            public int compare(Pair<Integer, SimpleSequence> o1, Pair<Integer, SimpleSequence> o2) {
                return o1.getFirst() - o2.getFirst();
            }
        });

        final Map<SimpleSequence, Integer> solution = new HashMap<SimpleSequence, Integer>();
        if (find(nrs, 6, 0, 0, solution)) {
            int sum = 0;
            for (final Integer nr : solution.values()) {
                sum += nr;
            }
            return sum;
        } else {
            throw new SolutionNotFoundException();
        }
    }
}
