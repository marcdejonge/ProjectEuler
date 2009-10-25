package euler.level1;

import java.util.SortedSet;
import java.util.TreeSet;

import euler.Problem;

public class Problem029 extends Problem<Integer> {

	@Override
	public Integer solve() {
		SortedSet<Integer> set = new TreeSet<Integer>();
		for (int a = 2; a <= 100; a++) {
			boolean found = false;
			for (int x = 2; x <= Math.sqrt(a) && !found; x++) {
				for (int y = x * x, i = 2; y <= a && !found; y *= x, i++) {
					if (y == a) {
						found = true;
						for (int b = 2; b <= 100; b++) {
							set.add(x * 1000 + b * i);
						}
					}
				}
			}
			if (!found) {
				for (int b = 2; b <= 100; b++) {
					set.add(a * 1000 + b);
				}
			}
		}

		return set.size();
	}

}
