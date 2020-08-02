package euler.graph;

public interface DirectedEdge<V extends Vertex<V, E>, E extends DirectedEdge<V, E>> extends Edge<V, E> {
    V getFrom();

    V getTo();
}
