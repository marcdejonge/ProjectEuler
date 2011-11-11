package euler.sequence;

public class Fibionacci extends AbstractSequence {
	private long last, current;
	private int ix;
	
	public Fibionacci() {
		last = current = 1;
		ix = 0;
	}

	@Override
	public long current() {
		return current;
	}

	@Override
	public long next() {
		long next = last + current;
		last = current;
		current = next;
		return current;
	}

	@Override
	public int position() {
		return ix;
	}
}
