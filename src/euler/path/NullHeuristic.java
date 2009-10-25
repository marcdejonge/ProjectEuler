package euler.path;

public class NullHeuristic implements Heuristic {
	@Override
	public long estimateDistance(Node from, Node to) {
		return 0;
	}
}
