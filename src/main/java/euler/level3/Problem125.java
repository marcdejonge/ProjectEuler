package euler.level3;

import java.util.stream.LongStream;

import euler.IntegerProblem;
import euler.sequence.LongSortedSet;

public class Problem125 extends IntegerProblem {
    private static final int LIMIT = 100_000_000;

    private static boolean isPalindrome(long x) {
        if (x <= 0) {
            return false;
        } else if (x < 10) {
            return true;
        }
        long div = 10;
        while (x / (div * 10) >= 1) {
            div *= 10;
        }
        while (div >= 10) {
            long last = x % 10;
            long first = x / div;
            if (first != last) {
                return false;
            }
            x -= first * div;
            x /= 10;
            div /= 100;
        }
        return true;
    }

    @Override
    public long solve() throws Exception {
        return LongStream.range(1, (long) Math.sqrt(LIMIT / 2))
                         .parallel()
                         .flatMap(start -> {
                             LongSortedSet results = new LongSortedSet();
                             long nr = start;
                             long sum = nr * nr + (nr + 1) * (nr + 1);
                             nr += 2;
                             while (sum < LIMIT) {
                                 if (isPalindrome(sum)) {
                                     print("Found sum: %d (%d - %d)%n", sum, start, nr - 1);
                                     results.add(sum);
                                 }
                                 sum += nr * nr;
                                 nr++;
                             }
                             return results.longStream();
                         }).distinct().sum();
    }
}
