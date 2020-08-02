package euler.sequence;

public class HeptagonalNumbers extends SimpleSequence {
    @Override
    public long current() {
        return getN() * (5 * getN() - 3) / 2;
    }
}
