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

public class Problem083 extends Problem<Long> {
    @Override
    public Long solve() {
        final List<List<Node>> nodes = new ArrayList<List<Node>>(80);
        try (final BufferedReader reader = new BufferedReader(new FileReader("Problem083.txt"))) {
            String line = null;
            int y = 0;
            while ((line = reader.readLine()) != null) {
                // Parse the line!
                final String[] numbers = line.split(",");

                final List<Node> currentNodes = new ArrayList<Node>(80);

                for (int x = 0; x < numbers.length; x++) {
                    final int number = Integer.parseInt(numbers[x]);
                    currentNodes.add(new Node(number, x, y));
                }
                nodes.add(currentNodes);

                y++;
            }
            // Link all the nodes, first horizontal
            for (final List<Node> list : nodes) {
                Node curr = null;
                for (final Node next : list) {
                    if (curr != null) {
                        curr.addNeighbor(next);
                        next.addNeighbor(curr);
                    }
                    curr = next;
                }
            }
            // Now vertical
            List<Node> curr = null;
            for (final List<Node> next : nodes) {
                if (curr != null) {
                    for (int x = 0; x < Math.min(next.size(), curr.size()); x++) {
                        curr.get(x).addNeighbor(next.get(x));
                        next.get(x).addNeighbor(curr.get(x));
                    }
                }
                curr = next;
            }
        } catch (final IOException ex) {
            ex.printStackTrace();
            return null;
        }

        final Node start = nodes.get(0).get(0);
        final List<Node> goalList = nodes.get(nodes.size() - 1);
        final Node goal = goalList.get(goalList.size() - 1);

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
        final List<Node> shortestPath = solver.findShortestPath();

        long totalLength = 0;
        for (final Node n : shortestPath) {
            totalLength += n.getValue();
        }

        return totalLength;
    }
}
