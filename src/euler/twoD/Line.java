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

	public boolean intersects(Line other) {
		int n = dx * other.dy - dy * other.dx;
		if (n == 0) { // Parallel lines
			return false;
		}

		int dx1 = x1 - other.x1;
		int dy1 = y1 - other.y1;
		int r = (dy1 * other.dx - dx1 * other.dy);
		int s = (dy1 * dx - dx1 * dy);

		return r > 0 && r < n && s > 0 && s < n;
	}

	@Override
	public String toString() {
		return String.format("(%d,%d)-(%d,%d)", x1, y1, x2, y2);
	}
}
