package euler.level3;

import euler.IntegerProblem;

public class Problem106 extends IntegerProblem {
    public static int choose(int n, int r) {
        long top = 1, bottom = 1;
        for (int i = 0; i < r; i++) {
            top *= n - i;
            bottom *= i + 1;
        }
        return (int) (top / bottom);
    }

    // @formatter:off
    // 
    // For n = 7:
    //
    // A B B A
    // 1 * 7n4 = 35
    // 
    // A A B B B A
    // A B A B B A
    // A B B A A B
    // A B B A B A
    // A B B B A A
    // 5 * 7n6 = 35
    // Find the number of "unmatched params"
    // These are the options that we don't want
    // So we want every option starting with A (2*n-1 nCr n) minus Cn, the number of matching parameters for n.
    // 
    //@formatter:on

    public static int count(int n) {
        return choose(2 * n - 1, n) - matchedPairs(n, n);
    }

    public static int matchedPairs(int leftParam, int rightParam) {
        if (rightParam <= leftParam) {
            return matchedPairs(leftParam - 1, rightParam);
        } else if (leftParam == 0 || rightParam == 0) {
            return 1;
        } else {
            return matchedPairs(leftParam - 1, rightParam) + matchedPairs(leftParam, rightParam - 1);
        }
    }

    @Override
    public long solve() {
        int n = 12;
        int total = 0;
        for (int x = 2; x * 2 <= n; x++) {
            total += count(x) * choose(n, x * 2);
        }
        return total;
    }
}
