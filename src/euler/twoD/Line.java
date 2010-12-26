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

	public boolean crosses(Line l) {
		double n = dx * l.dy - dy * l.dx;
		if (n == 0) { // Parallel lines
			return false;
		}

		int dx1 = x1 - l.x1;
		int dy1 = y1 - l.y1;
		double r = (dy1 * l.dx - dx1 * l.dy) / n;
		double s = (dy1 * dx - dx1 * dy) / n;

		return r > 0 && r < 1 && s > 0 && s < 1;
	}

	@Override
	public String toString() {
		return String.format("(%d,%d)-(%d,%d)", x1, y1, x2, y2);
	}
}
