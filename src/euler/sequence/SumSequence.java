package euler.sequence;

public class SumSequence extends Sequence {
	public static void main(String[] args) {
		System.out.println(new SumSequence(new NaturalNumbers()));
	}

	private final Sequence seq;

	private long sum;

	public SumSequence(Sequence seq) {
		this.seq = seq;
		sum = 0;
	}

	@Override
	public long current() {
		return sum;
	}

	@Override
	public long next() {
		sum += seq.next();
		return sum;
	}

	@Override
	public int position() {
		return seq.position();
	}

	@Override
	public void reset() {
		seq.reset();
		sum = 0;
	}
}
