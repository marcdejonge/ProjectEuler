package euler.graph;

import java.util.Comparator;
import java.util.PriorityQueue;

public class PrimsAlgorithm<V extends Vertex<V, E>, E extends WeightedEdge<V, E> & DirectedEdge<V, E>> {
    public static <V extends Vertex<V, E>, E extends WeightedEdge<V, E> & DirectedEdge<V, E>>
            PrimsAlgorithm<V, E>
            create(Graph<V, E> inputGraph) {
        return new PrimsAlgorithm<>(inputGraph);
    }

    private final Graph<V, E> inputGraph;

    public PrimsAlgorithm(Graph<V, E> inputGraph) {
        this.inputGraph = inputGraph;
    }

    public WeightedDirectedGraphImpl run() {
        PriorityQueue<E> edges = new PriorityQueue<E>(11, new Comparator<E>() {
            @Override
            public int compare(E e1, E e2) {
                if (e1.getWeight() != e2.getWeight()) {
                    return e1.getWeight() - e2.getWeight();
                } else if (e1.getFrom() != e2.getFrom()) {
                    return e1.getFrom().hashCode() - e2.getFrom().hashCode();
                } else {
                    return e1.getTo().hashCode() - e2.getTo().hashCode();
                }
            }
        });

        WeightedDirectedGraphImpl outputGraph = new WeightedDirectedGraphImpl();

        V vertex = inputGraph.getAnyVertex();
        while (true) {
            for (E edge : vertex) {
                edges.add(edge);
            }

            E nextEdge = edges.poll();
            while (nextEdge != null && outputGraph.hasVertex(nextEdge.getTo().getId())) {
                nextEdge = edges.poll();
            }
            if (nextEdge != null) {
                outputGraph.addEdge(nextEdge);
                vertex = nextEdge.getTo();
            } else {
                break;
            }
        }

        return outputGraph;
    }
}
