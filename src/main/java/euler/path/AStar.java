package euler.path;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import euler.JavaPair;

public class AStar implements ShortestPathSolver {
    public static interface Guide {
        long estimateDistanceToGoal(Node node);

        boolean isGoal(Node node);
    }

    private final Node start;

    private final Guide guide;

    public AStar(Node start, Guide guide) {
        this.start = start;
        this.guide = guide;
    }

    @Override
    public List<Node> findShortestPath() {
        final Map<Node, JavaPair<Node, Long>> closed = new HashMap<Node, JavaPair<Node, Long>>(100);
        final Map<Node, JavaPair<Node, Long>> open = new HashMap<Node, JavaPair<Node, Long>>(100);

        open.put(start, JavaPair.from((Node) null, start.getValue() + guide.estimateDistanceToGoal(start)));

        while (!open.isEmpty()) {
            long min = Long.MAX_VALUE;
            Node current = null, from = null;
            for (final Entry<Node, JavaPair<Node, Long>> entry : open.entrySet()) {
                if (entry.getValue().getSecond() < min) {
                    min = entry.getValue().getSecond();
                    current = entry.getKey();
                    from = entry.getValue().getFirst();
                }
            }

            open.remove(current);
            final long distanceUntilNow = (from == null ? 0 : closed.get(from).getSecond()) + current.getValue();
            closed.put(current, JavaPair.from(from, distanceUntilNow));

            if (guide.isGoal(current)) {
                return reconstruct(closed, current);
            }

            for (final Node neighbor : current) {
                if (closed.containsKey(neighbor)) {
                    continue;
                }

                final long estimatedDistance = distanceUntilNow + neighbor.getValue() + guide.estimateDistanceToGoal(current);
                if (!open.containsKey(neighbor) || estimatedDistance < open.get(neighbor).getSecond()) {
                    open.remove(neighbor);
                    open.put(neighbor, JavaPair.from(current, estimatedDistance));
                }
            }
        }

        return null;
    }

    private List<Node> reconstruct(Map<Node, JavaPair<Node, Long>> closed, Node current) {
        final List<Node> result = new LinkedList<Node>();
        while (current != null) {
            result.add(0, current);
            final JavaPair<Node, Long> pair = closed.get(current);
            current = pair == null ? null : pair.getFirst();
        }
        return result;
    }
}
