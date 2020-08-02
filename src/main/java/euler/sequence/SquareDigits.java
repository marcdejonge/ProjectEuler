/**
 *
 */
package euler.sequence;

public class SquareDigits extends AbstractSequence {
    private final int n;

    private int sqN, a, num, den, pos;

    public SquareDigits(int n) {
        this.n = n;
        reset();
    }

    @Override
    public long current() {
        return a;
    }

    public boolean equalPoint(SquareDigits other) {
        return n == other.n && a == other.a && num == other.num && den == other.den;
    }

    public int getSquareN() {
        return sqN;
    }

    @Override
    public long next() {
        final int num = (n - den * den) / this.num;
        if (num == 0) {
            a = 0;
            pos++;
        } else {
            final int sum = sqN + den;
            final int a = sum / num;
            final int den = -(this.den - a * num);

            pos++;
            this.a = a;
            this.num = num;
            this.den = den;
        }

        return current();
    }

    @Override
    public long position() {
        return pos;
    }

    @Override
    public SquareDigits reset() {
        pos = 0;
        sqN = (int) Math.floor(Math.sqrt(n));
        a = 0;
        num = n;
        den = 0;
        return this;
    }
}