package euler.graph;

public interface WeightedEdge<V extends Vertex<V, E>, E extends WeightedEdge<V, E>> extends Edge<V, E> {
    int getWeight();
}
