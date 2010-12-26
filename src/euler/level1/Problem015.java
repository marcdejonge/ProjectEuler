package euler.level1;

import euler.Problem;

public class Problem015 extends Problem<Long> {

	@Override
	public Long solve() {
		int SIZE = 21;

		long[][] matrix = new long[SIZE][SIZE];
		matrix[SIZE - 1][SIZE - 1] = 1;
		for (int cnt = (SIZE * 2) - 3; cnt >= 0; cnt--) {
			for (int x = cnt; x >= 0; x--) {
				int y = cnt - x;
				if (x < SIZE && y < SIZE) {
					// Calculate
					matrix[x][y] = (x < SIZE - 1 ? matrix[x + 1][y] : 0)
									+ (y < SIZE - 1 ? matrix[x][y + 1] : 0);
				}
			}
		}
		return matrix[0][0];
	}

}
