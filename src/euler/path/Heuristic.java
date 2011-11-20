package euler.path;

public interface Heuristic {
    public long estimateDistance(Node from, Node to);
}
