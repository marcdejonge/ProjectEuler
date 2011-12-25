package euler.level3;

import euler.Problem;

public class Problem142 extends Problem<Integer> {
    private boolean isSquare(int x) {
        int sq = (int) Math.sqrt(x);
        return sq * sq == x;
    }

    // x + y = a^2
    // x - y = b^2
    // x + z = c^2
    // x - z = d^2
    // y + z = e^2
    // y - z = f^2
    //
    // a^2 + b^2 - c^2 = d^2
    // c^2 - b^2 = e^2
    // a^2 - c^2 = f^2
    // a > c > b
    //
    // x = (a^2 + b^2) / 2
    // y = x - b^2
    // z = c^2 - x
    @Override
    public Integer solve() {
        for (int a = 1;; a++) {
            int a2 = a * a;

            for (int c = 2 - a % 2; c < a; c += 2) {
                int c2 = c * c;

                // If f^2 is really square, then check b
                if (isSquare(a2 - c2)) {
                    for (int b = 2 - c % 2; b < c; b += 2) {
                        int b2 = b * b;

                        // Check if d^2 and e^2 are really square
                        if (isSquare(c2 - b2) && isSquare(a2 + b2 - c2)) {
                            int x = (a2 + b2) / 2;
                            int y = x - b2;
                            int z = c2 - x;
                            if (z > 0) {
                                return x + y + z;
                            }
                        }
                    }
                }
            }
        }
    }

}
