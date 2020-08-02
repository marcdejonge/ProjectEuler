package euler.graph;

public interface Vertex<V extends Vertex<V, E>, E extends Edge<V, E>> extends Iterable<E> {
    int getId();
}
