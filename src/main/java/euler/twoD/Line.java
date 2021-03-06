package euler.twoD;

public class Line implements Comparable<Line> {

    private static boolean inbetween(double a, double b, double c) {
        return a < b && b < c || a > b && b > c || a == b && b == c;
    }

    private final Point p1, p2;

    private final double dx, dy;
    private final double dc;

    public Line(int x1, int y1, int x2, int y2) {
        this(new Point(x1, y1), new Point(x2, y2));
    }

    public Line(Point p1, Point p2) {
        if (p1.x > p2.x) {
            this.p1 = p2;
            this.p2 = p1;
        } else {
            this.p1 = p1;
            this.p2 = p2;
        }

        dx = p1.x - p2.x;
        dy = p1.y - p2.y;
        dc = p1.x * p2.y - p1.y * p2.x;
    }

    @Override
    public int compareTo(Line o) {
        if (p1.x < o.p1.x) {
            return -1;
        } else if (p1.x > o.p1.x) {
            return 1;
        } else {
            return 0;
        }
    }

    public final boolean contains(Point p) {
        return inbetween(p1.x, p.x, p2.x) && inbetween(p1.y, p.y, p2.y);
    }

    public final double getCrossDiff() {
        return dc;
    }

    public Point getIntersection(Line other) {
        if (p1.x > other.p2.x || p2.x < other.p1.x) {
            return null;
        } else if (Math.min(p1.y, p2.y) > Math.max(other.p1.y, other.p2.y) || Math.min(other.p1.y, other.p2.y) > Math.max(p1.y, p2.y)) {
            return null;
        }

        // Calculation base on: http://en.wikipedia.org/wiki/Line-line_intersection#Mathematics
        double num = dx * other.dy - other.dx * dy;
        if (num == 0) {
            // The lines are parallel and can't cross
            return null;
        }

        double pxn = dc * other.dx - other.dc * dx;
        double pyn = dc * other.dy - other.dc * dy;
        Point cross = new Point(pxn / num, pyn / num);

        if (cross.equals(p1) || cross.equals(p2) || cross.equals(other.p1) || cross.equals(other.p2)) {
            return null;
        } else if (contains(cross) && other.contains(cross)) {
            return cross;
        } else {
            return null;
        }
    }

    public Point getP1() {
        return p1;
    }

    public Point getP2() {
        return p2;
    }

    // final int n = dx * other.dy - other.dx * dy;
    // if (n == 0) { // Parallel lines
    // return false;
    // }
    //
    // final int dxs = x1 - other.x1;
    // final int dys = y1 - other.y1;
    // final int r = dys * other.dx - dxs * other.dy;
    // final int s = dys * dx - dxs * dy;
    //
    // if (n > 0) {
    // return r > 0 && r < n && s > 0 && s < n;
    // } else {
    // return r > n && r < 0 && s > n && s < 0;
    // }

    public final boolean isParralelTo(final Line other) {
        return dx * other.dy - other.dx * dy == 0;
    }

    @Override
    public String toString() {
        return p1 + "-" + p2;
    }
}
