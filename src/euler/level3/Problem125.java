package euler.level3;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import euler.MultiCoreProblem;
import euler.sequence.NaturalNumbers;

public class Problem125 extends MultiCoreProblem {
    private static final Object STONE = new Object();

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

    public static void main(String[] args) {
        System.out.println(new Problem125(1000L).solve());
    }

    private final long limit;

    private final ConcurrentMap<Long, Object> numbers;

    public Problem125() {
        this(100000000L);
    }

    public Problem125(long limit) {
        super(new NaturalNumbers(), 20);
        this.limit = limit;
        numbers = new ConcurrentHashMap<Long, Object>();
    }

    @Override
    public boolean handleNumber(long nr) {
        if (nr * nr > limit) {
            return false;
        }
        long sum = nr * nr + (nr + 1) * (nr + 1);
        nr += 2;
        while (sum < limit) {
            if (isPalindrome(sum) && numbers.putIfAbsent(sum, STONE) == null) {
                result.addAndGet(sum);
            }
            sum += nr * nr;
            nr++;
        }
        return true;
    }

}
