package euler.twoD;

import euler.collection.Hashable;

public final class Point implements Hashable {
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
        long h = longHashcode();
        return (int) (h ^ (h >>> 32));
    }

    @Override
    public long longHashcode() {
        long tx = Double.doubleToLongBits(x);
        long ty = Double.doubleToLongBits(y);
        return PRIME * (PRIME * tx + ty);
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}
