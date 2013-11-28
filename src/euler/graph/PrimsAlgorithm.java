package euler.graph;

import java.util.Comparator;
import java.util.PriorityQueue;

import euler.graph.WeightedGraph.Edge;
import euler.graph.WeightedGraph.Vertex;

public class PrimsAlgorithm {
    private final WeightedGraph inputGraph;

    public PrimsAlgorithm(WeightedGraph inputGraph) {
        this.inputGraph = inputGraph;
    }

    public WeightedGraph run() {
        PriorityQueue<Edge> edges = new PriorityQueue<Edge>(11, new Comparator<Edge>() {
            @Override
            public int compare(Edge e1, Edge e2) {
                if (e1.getWeight() != e2.getWeight()) {
                    return e1.getWeight() - e2.getWeight();
                } else if (e1.getFrom() != e2.getFrom()) {
                    return e1.getFrom() - e2.getFrom();
                } else {
                    return e1.getTo() - e2.getTo();
                }
            }
        });

        WeightedGraph outputGraph = new WeightedGraph();

        Vertex vertex = inputGraph.iterator().next();
        while (true) {
            for (Edge edge : vertex) {
                edges.add(edge);
            }

            Edge nextEdge = edges.poll();
            while (nextEdge != null && outputGraph.hasVertex(nextEdge.getTo())) {
                nextEdge = edges.poll();
            }
            if (nextEdge != null) {
                outputGraph.addEdge(nextEdge);
                vertex = inputGraph.getVertex(nextEdge.getTo());
            } else {
                break;
            }
        }

        return outputGraph;
    }
}
