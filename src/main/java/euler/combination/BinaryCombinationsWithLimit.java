package euler.combination;

import java.util.HashMap;
import java.util.Map;

import euler.Pair;

public class BinaryCombinationsWithLimit {
    private final int totalLength, maxRun;

    private final Map<Pair<Integer, Integer>, Long> solutions = new HashMap<Pair<Integer, Integer>, Long>();

    public BinaryCombinationsWithLimit(int totalLength, int maxRun) {
        this.totalLength = totalLength;
        this.maxRun = maxRun;
    }

    public long getTotalOptions() {
        return options(0, 0);
    }

    public long options(int nrReds, int depth) {
        if (depth == totalLength) {
            return nrReds == 0 || nrReds >= maxRun ? 1 : 0;
        } else {
            Pair<Integer, Integer> key = Pair.from(nrReds, depth);
            if (solutions.containsKey(key)) {
                return solutions.get(key);
            } else {
                long result = options(nrReds + 1, depth + 1);
                if (nrReds == 0 || nrReds >= maxRun) {
                    result += options(0, depth + 1);
                }
                solutions.put(key, result);
                return result;
            }
        }
    }
}
