package euler.path;

import java.util.Iterator;
import java.util.List;

public class Node implements Iterable<Node> {
    public static int sumValues(List<Node> nodes) {
        int total = 0;
        for (final Node node : nodes) {
            total += node.value;
        }
        return total;
    }

    private Node[] neighbors;

    private final int value;

    private final int x, y;

    public Node(int value, int x, int y) {
        this.value = value;
        this.x = x;
        this.y = y;
        neighbors = new Node[0];
    }

    public void addNeighbor(Node neighbor) {
        final Node[] temp = new Node[neighbors.length + 1];
        System.arraycopy(neighbors, 0, temp, 0, neighbors.length);
        temp[temp.length - 1] = neighbor;
        neighbors = temp;
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
        return new Iterator<Node>() {
            private int count = 0;

            @Override
            public boolean hasNext() {
                return count < neighbors.length;
            }

            @Override
            public Node next() {
                return neighbors[count++];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override
    public String toString() {
        return String.format("(Node <%d,%d>: %d)", x, y, value);
    }
}
