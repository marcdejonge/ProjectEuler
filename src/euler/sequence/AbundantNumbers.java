package euler.sequence;

public class AbundantNumbers extends AbstractSequence {
	public static void main(String[] args) {
		System.out.println(new AbundantNumbers().toString(62));
	}
	private long nr;

	private int pos;

	@Override
	public void reset() {
		nr = 0;
		pos = 0;
	}

	@Override
	public long current() {
		return nr;
	}

	@Override
	public long next() {
		pos++;
		for (nr = nr + 1; Primes.sumOfProperDivisors(nr) <= nr; nr++) {
		}
		return nr;
	}

	@Override
	public long position() {
		return pos;
	}
}
