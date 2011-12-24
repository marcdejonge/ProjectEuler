package euler.level3;

import java.util.HashMap;
import java.util.Map;

import euler.Pair;
import euler.Problem;

public class Problem114 extends Problem<Long> {
    private final Map<Pair<Integer, Integer>, Long> solutions = new HashMap<Pair<Integer, Integer>, Long>();

    private long options(int length, int nrReds, int depth) {
        if (depth == length) {
            return nrReds == 1 || nrReds == 2 ? 0 : 1;
        } else {
            Pair<Integer, Integer> key = Pair.from(nrReds, depth);
            if (solutions.containsKey(key)) {
                return solutions.get(key);
            } else if (nrReds == 1 || nrReds == 2) {
                // Now we must place a third red one!
                long result = options(length, nrReds + 1, depth + 1);
                solutions.put(key, result);
                return result;
            } else {
                long result = options(length, nrReds + 1, depth + 1) + options(length, 0, depth + 1);
                solutions.put(key, result);
                return result;
            }
        }
    }

    @Override
    public Long solve() {
        return options(50, 0, 0);
    }

}
