package euler.sequence;

public class TriangleNumbers extends SimpleSequence {
	@Override
	public long current() {
		return (getN() * (getN() + 1)) / 2;
	}
}
