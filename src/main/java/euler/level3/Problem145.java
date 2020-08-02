package euler.level3;

import static euler.numberic.NumericUtils.pow;
import euler.IntegerProblem;

public class Problem145 extends IntegerProblem {
    @Override
    public long solve() {
        long count = 0;

        for (int digitLength = 1; digitLength < 9; digitLength++) {
            if (digitLength % 2 == 0) {
                // For even length solutions, each pair of number should add up to an odd number < 10 (30 choices)
                // The first and last digit can not use 0, so it only has 20 choices
                count += 20 * pow(30, digitLength / 2 - 1);
            } else if ((digitLength - 1) % 4 == 0) {
                // For odd length in the form 4n + 1, there is no solution
            } else {
                // For odd length in the form 4n + 3,
                // 5 options for the middle one (should be even < 10)
                // 20 options for the outer layer and the 3rd / 5th / etc. layer
                // 25 options for the 2nd / 4th / etc. layer
                int n = (digitLength - 3) / 4;
                count += 5 * Math.pow(20, n + 1) * Math.pow(25, n);
            }
        }

        return count;
    }
}
