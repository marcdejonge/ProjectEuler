package euler.combination;

import java.util.HashMap;
import java.util.Map;

public class BinaryCombinationsWithFixedLength {
    private final int totalLength;

    private final int[] blockLengths;

    private final Map<Integer, Long> solutions = new HashMap<Integer, Long>();

    public BinaryCombinationsWithFixedLength(int totalLength, int... blockLengths) {
        this.totalLength = totalLength;
        this.blockLengths = blockLengths;
    }

    public long getTotalOptions() {
        return options(0);
    }

    public long options(int depth) {
        if (depth >= totalLength) {
            return depth == totalLength ? 1 : 0;
        } else if (solutions.containsKey(depth)) {
            return solutions.get(depth);
        } else {
            long result = options(depth + 1);
            for (int blockLength : blockLengths) {
                result += options(depth + blockLength);
            }
            solutions.put(depth, result);
            return result;

        }
    }
}
