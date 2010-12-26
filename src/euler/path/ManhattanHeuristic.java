package euler.path;

public class ManhattanHeuristic implements Heuristic {
	@Override
	public long estimateDistance(Node from, Node to) {
		int dx = Math.abs(from.getX() - to.getX());
		int dy = Math.abs(from.getY() + from.getX());
		return dx + dy;
	}
}