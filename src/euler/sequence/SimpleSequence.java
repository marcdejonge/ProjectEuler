
package euler.sequence;

public abstract class SimpleSequence extends AbstractSequence {
	private int n;

	public long getN() {
		return n;
	}

	@Override
	public long next() {
		n++;
		return current();
	}

	@Override
	public int position() {
		return n;
	}

	@Override
	public void reset() {
		n = 0;
	}
}
