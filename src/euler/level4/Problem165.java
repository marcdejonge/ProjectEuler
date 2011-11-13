package euler.level4;

import euler.Problem;
import euler.sequence.AbstractSequence;
import euler.twoD.Line;

public class Problem165 extends Problem<Integer> {
	
	private static class BlumBlumShub extends AbstractSequence {
		private static final int START = 290797;
		private static final int MOD = 500;
		private static final int GENMOD = 50515093;
		
		private long s;
		private long curr;
		private int pos;

		@Override
		public long current() {
			return curr;
		}

		@Override
		public long next() {
			s = (s * s) % GENMOD;
			curr = s % MOD;
			return curr;
		}

		@Override
		public long position() {
			return pos;
		}
		
		@Override
		public void reset() {
			s = START;
			curr = s % MOD;
			pos = 0;
		}
	}

	@Override
	public Integer solve() {
		BlumBlumShub generator = new BlumBlumShub();
		Line[] lines = new Line[5000];
		for(int ix = 0; ix < lines.length; ix++) {
			lines[ix] = new Line((int)generator.next(), (int)generator.next(), (int)generator.next(), (int)generator.next());
			System.out.println(lines[ix]);
		}
		
		int total = 0;
		for(int ix = 0; ix < lines.length; ix++) {
			for(int jx = ix + 1; jx < lines.length; jx++) {
				if(lines[ix].intersects(lines[jx])) {
					total++;
				}
			}
		}
		return total;
	}
}
