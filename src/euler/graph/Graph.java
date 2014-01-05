package euler.graph;

public interface Graph<V extends Vertex<V, E>, E extends Edge<V, E>> extends Iterable<V> {
    V getAnyVertex();

    V getVertex(int id);
}
