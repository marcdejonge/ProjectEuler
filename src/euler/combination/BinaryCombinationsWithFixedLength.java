package euler.combination;

import java.util.HashMap;
import java.util.Map;

public class BinaryCombinationsWithFixedLength {
    private final int totalLength, blockLength;

    private final Map<Integer, Long> solutions = new HashMap<Integer, Long>();

    public BinaryCombinationsWithFixedLength(int totalLength, int blockLength) {
        this.totalLength = totalLength;
        this.blockLength = blockLength;
    }

    public long getTotalOptions() {
        return options(0) - 1; // Ignore the option where we choose none
    }

    public long options(int depth) {
        if (depth >= totalLength) {
            return depth == totalLength ? 1 : 0;
        } else {
            if (solutions.containsKey(depth)) {
                return solutions.get(depth);
            } else {
                long result = options(depth + 1) + options(depth + blockLength);
                solutions.put(depth, result);
                return result;
            }
        }
    }
}
