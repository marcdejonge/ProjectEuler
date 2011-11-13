
package euler.sequence;

public abstract class SimpleSequence extends AbstractSequence {
	private long n;

	public long getN() {
		return n;
	}
	
	protected void setN(long start) {
		this.n = start;
	}

	@Override
	public long next() {
		n++;
		return current();
	}

	@Override
	public long position() {
		return n;
	}

	@Override
	public void reset() {
		n = 0;
	}
}
