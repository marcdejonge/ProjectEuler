package euler.level2;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import euler.Problem;
import euler.path.AStar;
import euler.path.AStar.Guide;
import euler.path.Node;
import euler.path.NodeReader;
import euler.path.ShortestPathSolver;

public class Problem081 extends Problem<Integer> {
    @Override
    public Integer solve() {
        try {
            List<List<Node>> nodes = new NodeReader(new FileReader("Problem081.txt"), NodeReader.CONNECT_RIGHT | NodeReader.CONNECT_DOWN).readNodes();

            Node start = nodes.get(0).get(0);
            List<Node> lastRow = nodes.get(nodes.size() - 1);
            final Node goal = lastRow.get(lastRow.size() - 1);

            final ShortestPathSolver solver = new AStar(start, new Guide() {
                @Override
                public long estimateDistanceToGoal(Node from) {
                    final int dx = Math.abs(from.getX() - goal.getX());
                    final int dy = Math.abs(from.getY() - goal.getY());
                    return dx + dy;
                }

                @Override
                public boolean isGoal(Node node) {
                    return node.equals(goal);
                }
            });
            return Node.sumValues(solver.findShortestPath());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
