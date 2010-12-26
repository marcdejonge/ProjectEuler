package euler.path;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import euler.Pair;

public class AStar implements ShortestPathSolver {
	private final Node goal;

	private final Heuristic heuristic;

	private final Node start;

	public AStar(Node start, Node goal, Heuristic heuristic) {
		this.start = start;
		this.goal = goal;
		this.heuristic = heuristic;
	}

	@Override
	public List<Node> findShortestPath() {
		Map<Node, Pair<Node, Long>> closed = new HashMap<Node, Pair<Node, Long>>();
		Map<Node, Pair<Node, Long>> open = new HashMap<Node, Pair<Node, Long>>();

		open.put(start, Pair.from((Node) null, start.getValue()
												+ heuristic.estimateDistance(start, goal)));

		while (!open.isEmpty()) {
			long min = Long.MAX_VALUE;
			Node current = null, from = null;
			for (Entry<Node, Pair<Node, Long>> entry : open.entrySet()) {
				if (entry.getValue().getSecond() < min) {
					min = entry.getValue().getSecond();
					current = entry.getKey();
					from = entry.getValue().getFirst();
				}
			}

			open.remove(current);
			long distanceUntilNow = (from == null ? 0 : closed.get(from).getSecond())
									+ current.getValue();
			closed.put(current, Pair.from(from, distanceUntilNow));

			if (current.equals(goal)) {
				return reconstruct(closed);
			}

			for (Node neighbor : current) {
				if (closed.containsKey(neighbor)) {
					continue;
				}

				long estimatedDistance = distanceUntilNow + neighbor.getValue()
											+ heuristic.estimateDistance(current, goal);
				if (!open.containsKey(neighbor)
					|| estimatedDistance < open.get(neighbor).getSecond()) {
					open.remove(neighbor);
					open.put(neighbor, Pair.from(current, estimatedDistance));
				}
			}
		}

		return null;
	}

	private List<Node> reconstruct(Map<Node, Pair<Node, Long>> closed) {
		List<Node> result = new LinkedList<Node>();
		Node current = goal;
		while (current != null) {
			result.add(0, current);
			Pair<Node, Long> pair = closed.get(current);
			current = pair == null ? null : pair.getFirst();
		}
		return result;
	}
}
