package euler.twoD;

public final class Point {
    final double x, y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null || getClass() != obj.getClass()) {
            return false;
        } else {
            return equals((Point) obj);
        }
    }

    public boolean equals(Point p) {
        return x == p.x && y == p.y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public int hashCode() {
        long tx = Double.doubleToLongBits(x);
        long ty = Double.doubleToLongBits(y);
        return 31 * (int) (tx ^ tx >>> 32) + (int) (ty ^ ty >>> 32);
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}
