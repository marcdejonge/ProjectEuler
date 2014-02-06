package euler.level2;

import euler.IntegerProblem;

public class Problem094 extends IntegerProblem {
    private static final long MAX = 1000000000l;

    private boolean isSquare(long x) {
        long r = (long) Math.sqrt(x);
        return r * r == x;
    }

    @Override
    public long solve() {
        // Using generation of http://en.wikipedia.org/wiki/Pythagorean_triple
        // There are two scenario's, both can be derived from n
        // The first is where a = base, b = height, c = side
        // The second is where b = base, a = height, c = side
        // Then use a = m^2 - n^2, b = 2mn, c = m^2 + n^2 to derive every possible solution
        // For the first, abs(a-c) = abs(m^2 - 3*n^2) = 1
        // For the second, abs(4mn - m^2 - n^2) = 1
        long sum = 0;
        for (long n = 1;; n++) {
            long n2 = n * n;
            long m2 = n2 * 3;
            long side = m2 + n2;

            if (isSquare(m2 + 1)) {
                long perimeter = 3 * side + 4;
                if (perimeter > MAX) {
                    break;
                }
                // System.out.printf("(%d, %d)%n", side + 1, side + 2);
                sum += perimeter;

                // Find the second solution for the same n
                long m;
                for (m = (long) Math.sqrt(m2); 4 * m * n - m * m - n2 > 1; m++) {
                }
                side = m * m + n2;
                long diff = 4 * m * n - m * m - n2;
                // System.out.printf("(%d, %d)%n", side, side + diff);
                perimeter = 3 * side + diff;
                if (perimeter > MAX) {
                    break;
                }
                sum += perimeter;
            }
        }
        return sum;
    }
}
