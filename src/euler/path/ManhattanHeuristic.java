package euler.path;

public class ManhattanHeuristic implements Heuristic {
    @Override
    public long estimateDistance(Node from, Node to) {
        final int dx = Math.abs(from.getX() - to.getX());
        final int dy = Math.abs(from.getY() + from.getX());
        return dx + dy;
    }
}