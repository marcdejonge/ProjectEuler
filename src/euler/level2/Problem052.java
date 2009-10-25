package euler.level2;

import euler.Problem;
import euler.numberic.Number;

public class Problem052 extends Problem<Long> {
	@Override
	public Long solve() {
		for (int i = 100000;; i++) {
			Number n1 = Number.valueOf(i);
			Number sort = n1.sort();
			Number n2 = n1.add(n1);
			if (n2.sort().equals(sort)) {
				Number n3 = n2.add(n1);
				if (n3.sort().equals(sort)) {
					Number n4 = n3.add(n1);
					if (n4.sort().equals(sort)) {
						Number n5 = n4.add(n1);
						if (n5.sort().equals(sort)) {
							Number n6 = n5.add(n1);
							if (n6.sort().equals(sort)) {
								return n1.longValue();
							}
						}
					}
				}
			}
		}
	}
}
