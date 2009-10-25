package euler.level2;

import euler.Problem;
import euler.combination.SumCombinationSolver;
import euler.sequence.Primes;

public class Problem077 extends Problem<Integer> {
	private static final int MAX = 100;
	@Override
	public Integer solve() {
		SumCombinationSolver solver = new SumCombinationSolver(new Primes().head(MAX), MAX);
		for(int x = 2; x < MAX; x++) {
			if(solver.calc(x) > 5000) {
				return x;
			}
		}
		return null;
	}
}
