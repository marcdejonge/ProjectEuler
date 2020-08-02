package euler.level3;

import java.util.Arrays;

public class Problem136 extends Problem135 {
    @Override
    public long solve() throws Exception {
        return Arrays.stream(generateCounters(50000000))
                     .filter(x -> x == 1)
                     .count();
    }
}
