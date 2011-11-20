package euler.sequence;

public class PentagonalNumbers extends SimpleSequence {
    @Override
    public long current() {
        return getN() * (3 * getN() - 1) / 2;
    }
}
