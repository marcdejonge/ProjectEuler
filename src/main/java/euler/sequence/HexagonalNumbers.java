package euler.sequence;

public class HexagonalNumbers extends SimpleSequence {
    @Override
    public long current() {
        return getN() * (2 * getN() - 1);
    }
}
