package euler.level10;

import java.math.BigDecimal;
import java.math.MathContext;

import euler.FloatingPointProblem;

public class Problem493 extends FloatingPointProblem {
    private static final int ROUNDS = 20;
    private static final int COLORS = 7;
    private static final int FACTOR = 10;
    private static final int TOTAL_BALLS = COLORS * FACTOR;

    @Override
    public double solve() {
        double[][] chances = new double[ROUNDS][COLORS];
        chances[0][0] = 1;
        for (int roundIx = 1; roundIx < ROUNDS; roundIx++) {
            for (int colorIx = 0; colorIx < COLORS; colorIx++) {
                int ballsPickedBefore = roundIx;
                int colorsPickedBefore = colorIx + 1;
                double chanceBefore = chances[roundIx - 1][colorIx];

                if (colorIx == COLORS - 1) {
                    chances[roundIx][colorIx] += chanceBefore;
                } else {
                    double chanceNoNewColor = (FACTOR * colorsPickedBefore - ballsPickedBefore)
                                              / (double) (TOTAL_BALLS - ballsPickedBefore);
                    double chanceNewColor = 1 - chanceNoNewColor;
                    chances[roundIx][colorIx] += chanceNoNewColor * chanceBefore;
                    chances[roundIx][colorIx + 1] += chanceNewColor * chanceBefore;
                }
            }
        }

        double expectedColors = 0;
        for (int colorIx = 0; colorIx < COLORS; colorIx++) {
            print("Colors %d => %1.9f%% chance%n", colorIx + 1, chances[ROUNDS - 1][colorIx]);
            expectedColors += (colorIx + 1) * chances[ROUNDS - 1][colorIx];
        }

        // Make sure we only return 9 decimal places
        return BigDecimal.valueOf(expectedColors).round(new MathContext(10)).doubleValue();
    }
}
