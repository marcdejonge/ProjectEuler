package euler.graph;

import java.util.Iterator;
import java.util.Map.Entry;

import euler.collection.IntIntHashMap;
import euler.collection.IntObjectHashMap;
import euler.graph.WeightedGraph.Vertex;

public class WeightedGraph implements Iterable<Vertex> {
    public static class Edge {
        private final int from;
        private final int to;
        private final int weight;

        Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            } else if (obj == null || getClass() != obj.getClass()) {
                return false;
            } else {
                Edge other = (Edge) obj;
                return from == other.from && to == other.to && weight == other.weight;
            }
        }

        public int getFrom() {
            return from;
        }

        public int getTo() {
            return to;
        }

        public int getWeight() {
            return weight;
        }

        @Override
        public int hashCode() {
            return 31 * (31 * from + to) + weight;
        }

        @Override
        public String toString() {
            return "Edge(" + from + " -> " + to + ")";
        };
    }

    public static class Vertex implements Iterable<Edge> {
        private final IntIntHashMap edges;
        private final int nr;

        private final Iterable<Edge> edgeView = new Iterable<Edge>() {
            @Override
            public Iterator<Edge> iterator() {
                final Iterator<Entry<Integer, Integer>> it = edges.entrySet().iterator();
                return new Iterator<Edge>() {
                    @Override
                    public boolean hasNext() {
                        return it.hasNext();
                    }

                    @Override
                    public Edge next() {
                        Entry<Integer, Integer> x = it.next();
                        return new Edge(nr, x.getKey(), x.getValue());
                    }

                    @Override
                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                };
            }
        };

        Vertex(int nr) {
            this.nr = nr;
            edges = new IntIntHashMap();
        }

        void addEdge(int to, int weight) {
            edges.put(to, weight);
        }

        public int getNr() {
            return nr;
        }

        @Override
        public Iterator<Edge> iterator() {
            return edgeView.iterator();
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Vertex ").append(nr).append(":\t");
            for (Edge edge : this) {
                sb.append('(').append(edge.getWeight()).append(") -> ").append(edge.getTo()).append('\t');
            }
            return sb.toString();
        }
    }

    private final IntObjectHashMap<Vertex> vertices;

    public WeightedGraph() {
        vertices = new IntObjectHashMap<Vertex>();
    }

    public void addEdge(Edge edge) {
        addEdge(edge.getFrom(), edge.getTo(), edge.getWeight());
    }

    public void addEdge(int from, int to, int weight) {
        getOrCreateVertex(from).addEdge(to, weight);
        getOrCreateVertex(to);
    }

    public void addUndirectedEdge(int from, int to, int weight) {
        getOrCreateVertex(from).addEdge(to, weight);
        getOrCreateVertex(to).addEdge(from, weight);
    }

    public Vertex getOrCreateVertex(int id) {
        if (!vertices.containsKey(id)) {
            vertices.put(id, new Vertex(id));
        }
        return vertices.get(id);
    }

    public Vertex getVertex(int id) {
        if (!vertices.containsKey(id)) {
            return null;
        } else {
            return vertices.get(id);
        }
    }

    public boolean hasVertex(int id) {
        return vertices.containsKey(id);
    }

    @Override
    public Iterator<Vertex> iterator() {
        return vertices.values().iterator();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("WeightedGraph\n");
        for (Vertex vertex : this) {
            sb.append('\t').append(vertex).append('\n');
        }
        return sb.toString();
    }
}
