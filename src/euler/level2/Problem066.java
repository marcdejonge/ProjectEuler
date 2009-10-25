package euler.level2;

import euler.Pair;
import euler.Problem;
import euler.numberic.Number;
import euler.sequence.SquareDigits;
import euler.sequence.ContinuedFraction;

public class Problem066 extends Problem<Integer> {
	@Override
	public Integer solve() {
		Number maxX = Number.ZERO;
		int maxD = 0;
		int squareN = 2, squared = 4;
		for (int d = 2; d <= 1000; d++) {
			if (d == squared) {
				squareN++;
				squared = squareN * squareN;
			} else {
				ContinuedFraction est = new ContinuedFraction(new SquareDigits(d));
				
				Pair<Number, Number> pair = est.next();
				while(true) {
					Number diff = pair.getFirst().pow(2).subtract(Number.valueOf(d).multiply(pair.getSecond().pow(2)));
					if(diff.equals(Number.ONE)) {
						break;
					}
					pair = est.next();
				}
				
//				System.out.printf("Found %s^2 - %d * %s^2 = 1%n", pair.getFirst().toString(), d, pair.getSecond().toString());
				if (maxX.compareTo(pair.getFirst()) < 0) {
					maxX = pair.getFirst();
					maxD = d;
				}
			}
		}
		return maxD;
	}
}
