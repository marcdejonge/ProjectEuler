package euler.combination;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import euler.Pair;

public class PellSolver {
    private static final BigInteger MAX = BigInteger.valueOf(Long.MAX_VALUE);

    public static void main(String[] args) {
        PellSolver ps = new PellSolver(8);

        Pair<Long, Long> solution = ps.next();
        while (solution != null) {
            System.out.println(solution);
            solution = ps.next();
        }
    }

    private final int n;

    private int position;

    private final List<Pair<Long, Long>> solutions;

    public PellSolver(int n) {
        this.n = n;
        solutions = new ArrayList<Pair<Long, Long>>();
    }

    private Pair<Long, Long> combine(long x1, long y1, long x2, long y2) {
        BigInteger bx1 = BigInteger.valueOf(x1);
        BigInteger by1 = BigInteger.valueOf(y1);
        BigInteger bx2 = BigInteger.valueOf(x2);
        BigInteger by2 = BigInteger.valueOf(y2);

        BigInteger x = bx1.multiply(bx2).add(by1.multiply(by2).multiply(BigInteger.valueOf(n)));
        BigInteger y = bx1.multiply(by2).add(bx2.multiply(by1));
        if (x.compareTo(MAX) > 0 && y.compareTo(MAX) > 0) {
            return null;
        }
        return new Pair<Long, Long>(x.longValue(), y.longValue());
    }

    public int getN() {
        return n;
    }

    public Pair<Long, Long> next() {
        if (position >= solutions.size()) {
            // Create new solutions
            if (solutions.size() == 0) {
                solutions.add(solve(3, 2, 9 - n * 4));
            } else {
                int nrGenerated = solutions.size();
                for (int ix = 0; ix < nrGenerated; ix++) {
                    long x1 = solutions.get(ix).getFirst();
                    long y1 = solutions.get(ix).getSecond();
                    for (int jx = ix; jx < nrGenerated; jx++) {
                        long x2 = solutions.get(jx).getFirst();
                        long y2 = solutions.get(jx).getSecond();
                        Pair<Long, Long> newSolution = combine(x1, y1, x2, y2);
                        if (newSolution != null && !solutions.contains(newSolution)) {
                            solutions.add(newSolution);
                        }
                    }
                }
            }
        }
        if (position < solutions.size()) {
            return solutions.get(position++);
        } else {
            return null;
        }
    }

    // For solving, see http://en.wikipedia.org/wiki/Pell's_equation
    private Pair<Long, Long> solve(long a, long b, long k) {
        if (k == 1) {
            return new Pair<Long, Long>(a, b);
        } else if (k == -1) {
            return combine(a, b, a, b);
        } else {
            // Iterate according to the algorithm
            long ak = Math.abs(k);
            long m = 0;
            while ((a + b * m) % ak != 0) {
                m++;
            }
            // Now we have a minimal m, so we can calculate the next one
            long a1 = (a * m + n * b) / ak;
            long b1 = (a + b * m) / ak;
            long k1 = (m * m - n) / k;
            return solve(a1, b1, k1);
        }
    }
}
