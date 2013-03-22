package euler.level2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import euler.Problem;
import euler.path.AStar;
import euler.path.AStar.Guide;
import euler.path.Node;
import euler.path.ShortestPathSolver;

public class Problem067 extends Problem<Long> {
    @Override
    public Long solve() {
        Node start = null, goal = null;
        try (final BufferedReader reader = new BufferedReader(new FileReader("Problem67.txt"))) {
            String line = null;
            List<Node> last = null;
            int y = 0;
            while ((line = reader.readLine()) != null) {
                final String[] numbers = line.split(" ");
                final List<Node> curr = new ArrayList<Node>();
                int x = 0;
                for (final String nr : numbers) {
                    final int value = Integer.parseInt(nr);
                    final Node node = new Node(100 - value, x, y);
                    curr.add(node);
                    if (y > 0) {
                        if (x > 0) {
                            last.get(x - 1).addNeighbor(node);
                        }
                        if (x < last.size()) {
                            last.get(x).addNeighbor(node);
                        }
                    } else {
                        start = node;
                    }
                    x++;
                }
                last = curr;
                y++;
            }

            goal = new Node(100, 0, y);
            for (final Node node : last) {
                node.addNeighbor(goal);
            }
        } catch (final IOException ex) {
            return null;
        }

        final Node endGoal = goal;
        final ShortestPathSolver solver = new AStar(start, new Guide() {
            @Override
            public long estimateDistanceToGoal(Node node) {
                return 0;
            }

            @Override
            public boolean isGoal(Node node) {
                return node.equals(endGoal);
            }
        });
        final List<Node> shortestPath = solver.findShortestPath();
        // System.out.println(shortestPath);

        long totalLength = 0;
        for (final Node n : shortestPath) {
            totalLength += 100 - n.getValue();
        }

        return totalLength;
    }
}
