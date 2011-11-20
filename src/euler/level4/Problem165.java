package euler.level4;

import euler.MultiCoreProblem;
import euler.sequence.AbstractSequence;
import euler.sequence.NaturalNumbers;
import euler.twoD.Line;

public class Problem165 extends MultiCoreProblem {

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

	private final Line[] lines;

	public Problem165() {
		super(new NaturalNumbers(), 100);
		lines = new Line[5000];
		BlumBlumShub bbs = new BlumBlumShub();
		for (int ix = 0; ix < lines.length; ix++) {
			lines[ix] = new Line((int) bbs.next(), (int) bbs.next(),
					(int) bbs.next(), (int) bbs.next());
		}
	}

	@Override
	public boolean handleNumber(long nr) {
		int ix = (int) nr;
		if (ix >= lines.length) {
			return false;
		}

		for (int jx = ix + 1; jx < lines.length; jx++) {
			if (lines[ix].intersects(lines[jx])) {
				result.incrementAndGet();
			}
		}

		return true;
	}
}
