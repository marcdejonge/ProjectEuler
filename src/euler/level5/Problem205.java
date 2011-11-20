package euler.level5;

import euler.Problem;

public class Problem205 extends Problem<Double> {

    @Override
    public Double solve() {
        final int[] pyramidalDice = new int[] { 1, 2, 3, 4 };
        final int[] cubicDice = new int[] { 1, 2, 3, 4, 5, 6 };

        final int[] optionsPyramid = new int[40];
        double totalPyramid = 0;
        final int[] optionsCubic = new int[40];
        double totalCubic = 0;

        for (final int a : pyramidalDice) {
            for (final int b : pyramidalDice) {
                for (final int c : pyramidalDice) {
                    for (final int d : pyramidalDice) {
                        for (final int e : pyramidalDice) {
                            for (final int f : pyramidalDice) {
                                for (final int g : pyramidalDice) {
                                    for (final int h : pyramidalDice) {
                                        for (final int i : pyramidalDice) {
                                            optionsPyramid[a + b + c + d + e + f + g + h + i]++;
                                            totalPyramid++;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        for (final int a : cubicDice) {
            for (final int b : cubicDice) {
                for (final int c : cubicDice) {
                    for (final int d : cubicDice) {
                        for (final int e : cubicDice) {
                            for (final int f : cubicDice) {
                                optionsCubic[a + b + c + d + e + f]++;
                                totalCubic++;
                            }
                        }
                    }
                }
            }
        }

        double chance = 0;

        for (int i = 0; i < optionsCubic.length; i++) {
            if (optionsCubic[i] == 0) {
                continue;
            }
            int total = 0;
            for (int j = i + 1; j < optionsPyramid.length; j++) {
                total += optionsPyramid[j];
            }
            if (total == 0) {
                continue;
            }
            chance += optionsCubic[i] / totalCubic * total / totalPyramid;
        }

        return Math.round(chance * 1e7) / 1e7;
    }

}
