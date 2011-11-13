package euler.level5;

import euler.Problem;

public class Problem205 extends Problem<Double> {

	@Override
	public Double solve() {
		int[] pyramidalDice = new int[] { 1, 2, 3, 4 };
		int[] cubicDice = new int[] { 1, 2, 3, 4, 5, 6 };

		int[] optionsPyramid = new int[40];
		double totalPyramid = 0;
		int[] optionsCubic = new int[40];
		double totalCubic = 0;

		for (int a : pyramidalDice) {
			for (int b : pyramidalDice) {
				for (int c : pyramidalDice) {
					for (int d : pyramidalDice) {
						for (int e : pyramidalDice) {
							for (int f : pyramidalDice) {
								for (int g : pyramidalDice) {
									for (int h : pyramidalDice) {
										for (int i : pyramidalDice) {
											optionsPyramid[a + b + c + d + e
													+ f + g + h + i]++;
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

		for (int a : cubicDice) {
			for (int b : cubicDice) {
				for (int c : cubicDice) {
					for (int d : cubicDice) {
						for (int e : cubicDice) {
							for (int f : cubicDice) {
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
			chance += (optionsCubic[i] / totalCubic) * (total / totalPyramid);
		}

		return Math.round(chance * 1e7) / 1e7;
	}

}
