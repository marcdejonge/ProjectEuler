package euler.field;

public class Node {
    public static int maxSize = 1;

    private final Node left, right;

    private int minPath = -1, maxPath = -1;

    private Node minPathNode = null, maxPathNode = null;

    private final int nr;

    public Node(Node left, Node right, int nr) {
        this.left = left;
        this.right = right;
        this.nr = nr;
    }

    private void calcMaxPath() {
        final int lNr = left != null ? left.getMaxPath() : Integer.MIN_VALUE;
        final int rNr = right != null ? right.getMaxPath() : Integer.MIN_VALUE;
        if (lNr == Integer.MIN_VALUE && rNr == Integer.MIN_VALUE) {
            maxPath = nr;
            maxPathNode = null;
        } else if (lNr > rNr) {
            maxPath = lNr + nr;
            maxPathNode = left;
        } else {
            maxPath = rNr + nr;
            maxPathNode = right;
        }
    }

    private void calcMinPath() {
        final int lNr = left != null ? left.getMinPath() : Integer.MAX_VALUE;
        final int rNr = right != null ? right.getMinPath() : Integer.MAX_VALUE;
        if (lNr == Integer.MAX_VALUE && rNr == Integer.MAX_VALUE) {
            minPath = nr;
            minPathNode = null;
        } else if (lNr < rNr) {
            minPath = lNr + nr;
            minPathNode = left;
        } else {
            minPath = rNr + nr;
            minPathNode = right;
        }
    }

    public int getMaxPath() {
        if (maxPath == -1) {
            calcMaxPath();
        }
        return maxPath;
    }

    public Node getMaxPathNode() {
        if (maxPath == -1) {
            calcMaxPath();
        }
        return maxPathNode;
    }

    public int getMinPath() {
        if (minPath == -1) {
            calcMinPath();
        }
        return minPath;
    }

    public Node getMinPathNode() {
        if (minPath == -1) {
            calcMinPath();
        }
        return minPathNode;
    }

    public int getNr() {
        return nr;
    }

    @Override
    public String toString() {
        return String.format("%0" + Node.maxSize + "d", nr);
    }
}