package euler.twoD;

public class Point {
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
        if (Double.doubleToLongBits(x) != Double.doubleToLongBits(p.x)) {
            return false;
        } else if (Double.doubleToLongBits(y) != Double.doubleToLongBits(p.y)) {
            return false;
        } else {
            return true;
        }
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp = Double.doubleToLongBits(x);
        result = prime * result + (int) (temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(y);
        result = prime * result + (int) (temp ^ temp >>> 32);
        return result;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}
