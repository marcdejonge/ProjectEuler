package euler.graph;

public interface Edge<V extends Vertex<V, E>, E extends Edge<V, E>> {
    V[] getVertices();
}
