package euler.sequence;

public class PythagoreanTriplets {
    public static final class Triplet {
        private int a, b;
        private final int c;

        private Triplet(int a, int b, int c) {
            if (a < b) {
                this.a = a;
                this.b = b;
            } else {
                this.a = b;
                this.b = a;
            }
            this.c = c;
        }

        public int getA() {
            return a;
        }

        public int getB() {
            return b;
        }

        public int getC() {
            return c;
        }

        public int getSum() {
            return a + b + c;
        }

        @Override
        public String toString() {
            return String.format("(%d,%d,%d)", a, b, c);
        }
    }

    public static final int gcd(final int a, final int b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }

    private int v, w;

    public PythagoreanTriplets() {
        w = 0;
        v = 1;
    }

    public Triplet next() {
        do {
            v++;
            w--;
            if (w <= 0) {
                w = (v + 1) / 2;
                v = w + 1;
            }
        } while (gcd(v, w) > 1);

        final int v2 = v * v;
        final int w2 = w * w;
        return new Triplet(2 * v * w, v2 - w2, v2 + w2);
    }
}
