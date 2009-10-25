package euler.sequence;

public class SquareNumbers extends SimpleSequence {
	@Override
	public long current() {
		return getN() * getN();
	}
}
