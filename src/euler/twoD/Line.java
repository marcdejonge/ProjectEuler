package euler.twoD;

public class Line {

    private final int x1, y1, x2, y2;

    private final int dx, dy;

    public Line(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;

        dx = x2 - x1;
        dy = y2 - y1;
    }

    // dx = 2, dy = 0
    // odx = 0, ody = 2
    // n = 4
    // dxs = -1, dys = -1
    // r = 2
    // s = -2
    public boolean intersects(Line other) {
        final int n = dx * other.dy - other.dx * dy;
        if (n == 0) { // Parallel lines
            return false;
        }

        final int dxs = x1 - other.x1;
        final int dys = y1 - other.y1;
        final int r = dys * other.dx - dxs * other.dy;
        final int s = dys * dx - dxs * dy;

        if (n > 0) {
            return r > 0 && r < n && s > 0 && s < n;
        } else {
            return r > n && r < 0 && s > n && s < 0;
        }
    }

    @Override
    public String toString() {
        return String.format("(%d,%d)-(%d,%d)", x1, y1, x2, y2);
    }
}
