package euler.path;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Node implements Iterable<Node> {
    public static int sumValues(Iterable<Node> nodes) {
        int total = 0;
        for (final Node node : nodes) {
            total += node.value;
        }
        return total;
    }

    private final Set<Node> neighbors;

    private final int value;

    private final int x, y;

    public Node(int value, int x, int y) {
        this.value = value;
        this.x = x;
        this.y = y;
        neighbors = new HashSet<Node>(4);
    }

    public void addNeighbor(Node neighbor) {
        neighbors.add(neighbor);
    }

    public int getValue() {
        return value;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public Iterator<Node> iterator() {
        return neighbors.iterator();
    }

    @Override
    public String toString() {
        return String.format("(Node <%d,%d>: %d)", x, y, value);
    }
}
