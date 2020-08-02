package euler.sequence;

public class OctagonelNumbers extends SimpleSequence {
    @Override
    public long current() {
        return getN() * (3 * getN() - 2);
    }
}
