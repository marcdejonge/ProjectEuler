package euler.level1;

import euler.Problem;
import euler.sequence.HexagonalNumbers;
import euler.sequence.PentagonalNumbers;
import euler.sequence.Sequence;
import euler.sequence.TriangleNumbers;

public class Problem045 extends Problem<Long> {
	@Override
	public Long solve() {
		Sequence tria = new TriangleNumbers();
		Sequence pent = new PentagonalNumbers();
		Sequence hexa = new HexagonalNumbers();

		long t = tria.next();
		long p = pent.next();
		long h = hexa.next();
		while (true) {
			if (t == p && t == h) {
				if (t <= 40755) {
					h = hexa.next();
				} else {
					return t;
				}
			} else if (p > h) {
				h = hexa.next();
			} else if (t > p) {
				p = pent.next();
			} else {
				t = tria.next();
			}
		}
	}
}
