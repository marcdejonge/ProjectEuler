package euler.sequence;

import java.util.function.Supplier;

import euler.Triplet;

public class PythagoreanTriplets implements Supplier<Triplet> {
    public static final int gcd(final int a, final int b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }

    private volatile int v, w;

    public PythagoreanTriplets() {
        w = 0;
        v = 1;
    }

    @Override
    public Triplet get() {
        int v, w;

        synchronized (this) {
            v = this.v;
            w = this.w;
            do {
                v++;
                w--;
                if (w <= 0) {
                    w = (v + 1) / 2;
                    v = w + 1;
                }
            } while (gcd(v, w) > 1);
            this.v = v;
            this.w = w;
        }

        final int v2 = v * v;
        final int w2 = w * w;
        int a = 2 * v * w;
        int b = v2 - w2;
        int c = v2 + w2;
        if (a < b) {
            return new Triplet(a, b, c);
        } else {
            return new Triplet(b, a, c);
        }
    }
}
