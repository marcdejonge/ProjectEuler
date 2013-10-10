package euler.sequence;

public class NaturalNumbers extends SimpleSequence {

    public NaturalNumbers() {
    }

    public NaturalNumbers(long start) {
        setN(start - 1);
    }

    @Override
    public long current() {
        return getN();
    }
}
