package euler.twoD;

public class Vector {
    private final double x, y;

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double cross(Vector other) {
        return x * other.y - y * other.x;
    }

    public double dot(Vector other) {
        return other.x * x + other.y * y;
    }

    public Vector minus(Vector other) {
        return new Vector(x - other.x, y - other.y);
    }
}
