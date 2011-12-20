package euler.level2;

import euler.Problem;
import euler.sequence.Primes;

public class Problem091 extends Problem<Integer> {
    private final int MAX = 50;

    @Override
    public Integer solve() {
        int count = 0;

        // First, for the right angle at (0,0), there are 50 * 50 possibilities
        count += MAX * MAX;

        Primes primes = new Primes();

        // Now find any with a right angle at P
        for (int px = 0; px <= MAX; px++) {
            for (int py = 0; py <= MAX; py++) {
                if (px != 0 || py != 0) {
                    // Now calculate any point Q that is at an right angle from this line
                    int dx = py;
                    int dy = px;
                    primes.reset();
                    for (long prime : primes) {
                        while (dx % prime == 0 && dy % prime == 0) {
                            dx /= prime;
                            dy /= prime;
                        }
                        if (prime >= dx && prime >= dy) {
                            break;
                        }
                    }

                    // Now we can step through all possible Q's, both ways from P
                    for (int qx = px + dx, qy = py - dy; qx <= MAX && qy >= 0; qx += dx, qy -= dy) {
                        count++;
                    }
                    for (int qx = px - dx, qy = py + dy; qx >= 0 && qy <= MAX; qx -= dx, qy += dy) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

}
