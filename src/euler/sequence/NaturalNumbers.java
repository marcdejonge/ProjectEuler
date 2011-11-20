package euler.sequence;

public class NaturalNumbers extends SimpleSequence {

    public NaturalNumbers() {
    }

    public NaturalNumbers(long start) {
        setN(start);
    }

    @Override
    public long current() {
        return getN();
    }
}
