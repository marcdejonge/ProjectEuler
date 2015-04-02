package euler;

public class Triplet {
    public final int a, b, c;

    public Triplet(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null || getClass() != obj.getClass()) {
            return false;
        } else {
            Triplet other = (Triplet) obj;
            return a == other.a && b == other.b && c == other.c;
        }
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
    public int hashCode() {
        return 31 * a + 47 * b + 71 * c;
    }

    @Override
    public String toString() {
        return String.format("(%d,%d,%d)", a, b, c);
    }
}